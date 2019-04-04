package com.qf.controller;

import com.qf.service.SysUserService;
import com.qf.utils.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class EchartsController {

    @Resource
    private SysUserService sysUserService;

    @RequestMapping("/sys/echarts/pie")
    public R pie(){
        return sysUserService.findPieData();
    }

    @RequestMapping("/sys/echarts/bar")
    public R bar(){
        return sysUserService.findBarData();
    }
}
