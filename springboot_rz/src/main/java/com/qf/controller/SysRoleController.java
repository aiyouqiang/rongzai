package com.qf.controller;

import com.qf.service.SysRoleService;
import com.qf.utils.Pager;
import com.qf.utils.ResultData;
import com.qf.utils.Sorter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;

    @RequestMapping("/sys/role/list")
    public ResultData findRoleByPage(Pager pager, String search, Sorter sorter){
        return sysRoleService.findByPage(pager,search,sorter);
    }
}
