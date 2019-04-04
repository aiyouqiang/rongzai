package com.qf.quartz;

import com.alibaba.fastjson.JSON;
import com.qf.entity.ScheduleJob;
import com.qf.entity.ScheduleJobLog;
import com.qf.service.ScheduleJobLogService;
import com.qf.utils.Lg;
import com.qf.utils.SpringContextUtils;
import com.qf.utils.StringUtils;
import com.qf.utils.SysConstant;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.lang.reflect.Method;
import java.util.Date;


public class MyJob  implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
//         System.out.println("helloworld!!!!");

        ScheduleJobLog log = new ScheduleJobLog();
        long start = System.currentTimeMillis();

        try {
            //取出jobDetail封装的参数
            String json = (String) context.getJobDetail().getJobDataMap().get(SysConstant.SCHEDULE_DATA_KEY);
            ScheduleJob scheduleJob = JSON.parseObject(json,ScheduleJob.class);
            String beanName = scheduleJob.getBeanName();
            String methodName = scheduleJob.getMethodName();
            String params = scheduleJob.getParams();

            //在spring容器中，如何根据bean的名称得到这个对象
            //ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring.xml");
            //ac.getBean(beanName);

            Object object = SpringContextUtils.getBean(beanName);
            Class clazz = object.getClass();
            Method method= null;
            if (StringUtils.isEmpty(params)){
                method = clazz.getDeclaredMethod(methodName);
                method.invoke(object);
            }else{
                method = clazz.getDeclaredMethod(methodName,String.class);
                method.invoke(object,params);
            }

            log.setBeanName(beanName);
            log.setMethodName(beanName);
            log.setJobId(scheduleJob.getJobId());
            log.setCreateTime(new Date());
            log.setParams(params);
            log.setStatus(SysConstant.ScheduleStatus.NOMAL.getValue());


        } catch (Exception e) {
            e.printStackTrace();
            log.setError(e.getMessage());
        }
        long end = System.currentTimeMillis();
        ScheduleJobLogService scheduleJobLogService = (ScheduleJobLogService) SpringContextUtils.getBean("scheduleJobLogServiceImpl");
        log.setTimes((int) (end-start));
        scheduleJobLogService.insertLog(log);
        Lg.log("定时任务日志记录成功！");

    }
}
