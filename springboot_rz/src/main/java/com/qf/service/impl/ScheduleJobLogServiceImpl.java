package com.qf.service.impl;

import com.qf.entity.ScheduleJobLog;
import com.qf.mapper.ScheduleJobLogMapper;
import com.qf.service.ScheduleJobLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "scheduleJobLogServiceImpl")
public class ScheduleJobLogServiceImpl implements ScheduleJobLogService {
    @Resource
    private ScheduleJobLogMapper scheduleJobLogMapper;
    @Override
    public void insertLog(ScheduleJobLog scheduleJobLog) {
        scheduleJobLogMapper.insert(scheduleJobLog);
    }
}
