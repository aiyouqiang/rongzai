package com.qf.service.impl;

import com.qf.entity.SysLog;
import com.qf.mapper.SysLogMapper;
import com.qf.service.SysLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class SysLogServiceImpl implements SysLogService {

    //注入mapper
    @Resource
    private SysLogMapper sysLogMapper;

    @Override
    public int saveLog(SysLog sysLog) {
         return sysLogMapper.insert(sysLog);
    }
}
