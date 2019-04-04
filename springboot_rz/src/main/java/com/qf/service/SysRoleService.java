package com.qf.service;

import com.qf.utils.Pager;
import com.qf.utils.ResultData;
import com.qf.utils.Sorter;

import java.util.List;

public interface SysRoleService {

    public List<String> findRolesByUserId(long userId);

    public ResultData findByPage(Pager pager, String search
            , Sorter sorter);

}
