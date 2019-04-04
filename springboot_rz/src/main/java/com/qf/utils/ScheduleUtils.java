package com.qf.utils;

import com.alibaba.fastjson.JSON;
import com.qf.entity.ScheduleJob;
import com.qf.exception.RZException;
import com.qf.log.Mylog;
import com.qf.quartz.MyJob;
import org.quartz.*;

public class ScheduleUtils {

    public static void createScheduleTask(Scheduler scheduler, ScheduleJob scheduleJob){

        try {
            //1,ScheduleFactoryBean-->Scheduler对象
            //2,JobDetail
            //在mapper.xml文件中 insert语句： useGeneratedKeys="true" keyColumn="job_id" keyProperty="jobId"


            //创建JobDetail   持久化到数据库   表名  就是job的名称
            JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity(SysConstant.JOB_KEY_PREFIX+scheduleJob.getJobId()).build();

            //如何向任务类传递参数？
            //usingJobData() 方法一，使用usingJobData()方法传值
            String json = JSON.toJSONString(scheduleJob);
            jobDetail.getJobDataMap().put(SysConstant.SCHEDULE_DATA_KEY,json);
            //trigger
            CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(SysConstant.TRIGGER_KEY_PREFIX+scheduleJob.getJobId())
                    .withSchedule(CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression())).build();

            //注册任务和触发器
            scheduler.scheduleJob(jobDetail,cronTrigger);
        } catch (SchedulerException e) {
            throw new RZException("创建定时任务失败");
        }
    }



    public static void deleteScheduleTask(Scheduler scheduler,long jobId){
        try {
            JobKey jobKey = JobKey.jobKey(SysConstant.JOB_KEY_PREFIX+jobId);
            //删除定时任务
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            throw new RZException("删除定时任务失败");
        }
    }

    public static void updateScheduleTask(Scheduler scheduler,ScheduleJob scheduleJob){
        try {
            //trigger
            TriggerKey triggerKey = TriggerKey.triggerKey(SysConstant.TRIGGER_KEY_PREFIX+scheduleJob.getJobId());
            //获得 原来的触发器
            CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            //修改触发器的表达式
            CronTrigger newCronTrigger = cronTrigger.getTriggerBuilder().withSchedule
                    (CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression())).build();
            //重置触发器
            scheduler.rescheduleJob(triggerKey,newCronTrigger);
        } catch (SchedulerException e) {
            throw  new  RZException("修改任务失败，请联系管理员");
        }
    }

    public static void pause(Scheduler scheduler,long jobId){
        try {
            System.out.println(SysConstant.JOB_KEY_PREFIX+jobId);
            JobKey jobKey = JobKey.jobKey(SysConstant.JOB_KEY_PREFIX+jobId);
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            throw  new  RZException("暂停任务失败，请联系管理员");
        }
    }

    public static void resume(Scheduler scheduler,long jobIb){
        try {
            JobKey jobKey = JobKey.jobKey(SysConstant.JOB_KEY_PREFIX+jobIb);
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            throw  new  RZException("恢复任务失败，请联系管理员");
        }
    }

    public static void runOnce(Scheduler scheduler,long jobId){
        try {
            JobKey jobKey = JobKey.jobKey(SysConstant.JOB_KEY_PREFIX+jobId);
            scheduler.triggerJob(jobKey);
//            scheduler.start();
        } catch (SchedulerException e) {
            throw  new  RZException("执行任务失败，请联系管理员");
        }
    }

}
