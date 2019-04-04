package com.qf.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.entity.SysRole;
import com.qf.entity.SysRoleExample;
import com.qf.entity.SysUserExample;
import com.qf.mapper.SysRoleMapper;
import com.qf.service.SysRoleService;
import com.qf.utils.Pager;
import com.qf.utils.ResultData;
import com.qf.utils.Sorter;
import com.qf.utils.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("sysRoleServiceImpl")
public class SysRoleServiceImpl implements SysRoleService {

    //注入mapper
    @Resource
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<String> findRolesByUserId(long userId) {
        return sysRoleMapper.findRolesByUserId(userId);
    }

    @Override
    public ResultData findByPage(Pager pager, String search, Sorter sorter) {
        PageHelper.offsetPage(pager.getOffset(),pager.getLimit());
        SysRoleExample example = new SysRoleExample();

        if (sorter!=null&&StringUtils.isNotEmpty(sorter.getSort())){
            example.setOrderByClause("role_id"+sorter.getOrder());
        }
        SysRoleExample.Criteria criteria = example.createCriteria();
        if (search!=null&&!"".equals(search)){
            criteria.andRoleNameLike("%"+search+"%");
        }
        List<SysRole> list = sysRoleMapper.selectByExample(example);
        PageInfo info = new PageInfo(list);

        ResultData data = new ResultData(info.getTotal(),info.getList());

        return data;
    }
}
