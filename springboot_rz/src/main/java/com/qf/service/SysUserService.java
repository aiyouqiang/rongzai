package com.qf.service;

import com.qf.entity.SysUser;
import com.qf.utils.Pager;
import com.qf.utils.R;
import com.qf.utils.ResultData;
import com.qf.utils.Sorter;

import java.util.List;
import java.util.Map;

public interface SysUserService {
    public List<SysUser> findAll();

    public R login(SysUser sysUser);

    public SysUser findByUname(String name);

    public ResultData findByPage(Pager pager, String search, Sorter sorter);

    public R del(List<Long> ids);

    public R save(SysUser sysUser);

    public R findUser(long userId);

    public R update(SysUser sysUser);

    List<SysUser> findLockAccount();

    int unLockAccount(SysUser user);

    public R findPieData();

    public R findBarData();

    List<Map<String,Object>> exportExcel();

}
