$(function(){
    var option = {
        url: '../sys/role/list',
        pagination: true,	//显示分页条
        sidePagination: 'server',//服务器端分页
        showRefresh: true,  //显示刷新按钮
        search: true,
        toolbar: '#toolbar',
        striped : true,     //设置为true会有隔行变色效果
        //idField: 'menuId',
        columns: [
            {

                field: 'roleId',//field： json的key对应
                title: '序号',
                width: 40,
                formatter: function(value, row, index) {
                    var pageSize = $('#table').bootstrapTable('getOptions').pageSize;
                    var pageNumber = $('#table').bootstrapTable('getOptions').pageNumber;
                    return pageSize * (pageNumber - 1) + index + 1;
                }

            },
            {checkbox:true},
            { title: '角色ID', field: 'roleId',sortable:true},
            {field:'roleName', title:'角色'},
            { title: '备注', field: 'remark'},
            { title: '创建者ID', field: 'createUserId'},
            { title: '创建时间', field: 'createTime'}
        ]};
    $('#table').bootstrapTable(option);
});
var ztree;

var vm = new Vue({
	el:'#dtapp',
    data:{
        showList: true,
        title: null,
        role:{}
    },
    methods:{
        del: function(){
           // var rows = getSelectedRows();
            /**
             * getSelections
             参数： undefined
             详情：
             返回选定的行，如果未选择任何记录，则返回一个空数组。
             */
            var rows = $("#table").bootstrapTable("getSelections");
            if(rows == null||rows.length==0){
                layer.alert("请选择要删除的行");
                return ;
            }
            var id = 'roleId';
            //提示确认框
            layer.confirm('您确定要删除所选数据吗？', {
                btn: ['确定', '取消'] //可以无限个按钮
            }, function(index, layero){
                var ids = new Array();
                //遍历所有选择的行数据，取每条数据对应的ID
                $.each(rows, function(i, row) {
                    ids[i] = row[id];
                });

                $.ajax({
                    type: "POST",
                    url: "/sys/role/del",
                    data: JSON.stringify(ids),
                    success : function(r) {
                        if(r.code === 0){
                            layer.alert('删除成功');
                            $('#table').bootstrapTable('refresh');
                        }else{
                            layer.alert(r.msg);
                        }
                    },
                    error : function() {
                        layer.alert('服务器没有返回数据，可能服务器忙，请重试');
                    }
                });
            });
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";
            vm.role = {parentName:null,parentId:0,type:1,orderNum:0};
            vm.getMenu();
        },
        update: function (event) {
            var id = 'roleId';
            var roleId = getSelectedRow()[id];
            if(roleId == null){
                return ;
            }

            $.get("../sys/role/info/"+roleId, function(r){
                vm.showList = false;
                vm.title = "修改";
                vm.role = r.role;

                vm.getMenu();
            });
        },
        saveOrUpdate: function (event) {
            var url = vm.role.roleId == null ? "../sys/role/save" : "../sys/role/update";
            $.ajax({
                type: "POST",
                url: url,
                data: JSON.stringify(vm.role),
                success: function(r){
                    if(r.code === 0){
                        layer.alert('操作成功', function(index){
                            layer.close(index);
                            vm.reload();
                        });
                    }else{
                        layer.alert(r.msg);
                    }
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            $("#table").bootstrapTable('refresh');
        },
        menuTree: function(){
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "选择菜单",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#menuLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztree.getSelectedNodes();
                    //选择上级菜单
                    vm.menu.parentId = node[0].roleId;
                    vm.menu.parentName = node[0].role;

                    layer.close(index);
                }
            });
        },
        getMenu: function(roleId){

            var setting = {
                data: {
                    simpleData: {
                        enable: true,
                        idKey: "roleId",
                        pIdKey: "parentId",
                        rootPId: -1
                    },
                    key: {
                        url:"nourl"
                    }
                }
            };

            //加载菜单树
            $.get("../sys/role/select", function(r){
                //设置ztree的数据
                ztree = $.fn.zTree.init($("#roleTree"), setting, r.roleList);

                //编辑（update）时，打开tree，自动高亮选择的条目
                var node = ztree.getNodeByParam("roleId", vm.role.parentId);
                //选中tree菜单中的对应节点
                ztree.selectNode(node);
                //编辑（update）时，根据当前的选中节点，为编辑表单的“上级菜单”回填值
                vm.role.parentName = node.name;
            });
        }
    }
});