$(function(){
    var option = {
        url: '../sys/user/list',
        pagination: true,	//显示分页条
        sidePagination: 'server',//服务器端分页
        showRefresh: true,  //显示刷新按钮
        search: true,
        toolbar: '#toolbar',
        striped : true,     //设置为true会有隔行变色效果
        //idField: 'userId',
        columns: [
            {
                field: 'userId',
                title: '序号',
                width: 40,
                formatter: function(value, row, index) {
                    var pageSize = $('#table').bootstrapTable('getOptions').pageSize;
                    var pageNumber = $('#table').bootstrapTable('getOptions').pageNumber;
                    return pageSize * (pageNumber - 1) + index + 1;
                }
            },
            {checkbox:true},
            { title: '用户ID', field: 'userId',sortable:true},
            {field:'username', title:'用户名'},

            { title: '密码', field: 'password', formatter: function(value){
                    return '******';
                }},
            { title: '邮箱', field: 'email'},
            { title: '手机号', field: 'mobile'},
            { title: '创建时间', field: 'createTime'},
            { title: '性别', field: 'sex'}
        ]};
    $('#table').bootstrapTable(option);
});
var ztree;

var vm = new Vue({
	el:'#dtapp',
    data:{
        showList: true,
        title: null,
        user:{}
    },
    methods:{
	    exportExcel:function(){
	      console.log("导出！");
	      location.href="http://10.9.21.224:8080/exportExcel";
        },
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
            var id = 'userId';
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
                    url: "/sys/user/del",
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
            vm.user = {parentName:null,parentId:0,type:1,orderNum:0};
            vm.getUser();
        },
        update: function (event) {
            var id = 'userId';
            var userId = getSelectedRow()[id];
            if(userId == null){
                return ;
            }

            $.get("../sys/user/info/"+userId, function(r){
                vm.showList = false;
                vm.title = "修改";
                vm.user = r.user;

                vm.getUser();
            });
        },
        saveOrUpdate: function (event) {
            var url = vm.user.userId == null ? "../sys/user/save" : "../sys/user/update";
            $.ajax({
                type: "POST",
                url: url,
                data: JSON.stringify(vm.user),
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
        userTree: function(){
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "选择菜单",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#userLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztree.getSelectedNodes();
                    //选择上级菜单
                    vm.user.parentId = node[0].userId;
                    vm.user.parentName = node[0].name;

                    layer.close(index);
                }
            });
        },
        getUser: function(userId){

            var setting = {
                data: {
                    simpleData: {
                        enable: true,
                        idKey: "userId",
                        pIdKey: "parentId",
                        rootPId: -1
                    },
                    key: {
                        url:"nourl"
                    }
                }
            };

            //加载菜单树
            $.get("../sys/user/select", function(r){
                //设置ztree的数据
                ztree = $.fn.zTree.init($("#userTree"), setting, r.userList);

                //编辑（update）时，打开tree，自动高亮选择的条目
                var node = ztree.getNodeByParam("userId", vm.user.parentId);
                //选中tree菜单中的对应节点
                ztree.selectNode(node);
                //编辑（update）时，根据当前的选中节点，为编辑表单的“上级菜单”回填值
                vm.user.parentName = node.name;
            });
        }
    }
});