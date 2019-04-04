package com.qf.log;

import com.alibaba.fastjson.JSON;
import com.qf.entity.SysLog;
import com.qf.service.SysLogService;
import com.qf.utils.HttpContextUtils;
import com.qf.utils.IPUtils;
import com.qf.utils.ShiroUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect //使之成为切面类
@Component  //把切面类加入到IOC容器中
public class MyLogAspect {

    @Resource
    private SysLogService sysLogService;

    @Pointcut(value = "@annotation(com.qf.log.Mylog)")
    public void myPointcut(){}

    @AfterReturning(pointcut = "myPointcut()")
    public void after(JoinPoint joinPoint){
//        System.out.println("后置增强"+joinPoint.getTarget()+joinPoint.getSignature());
//        System.out.println("操作人"+ ShiroUtils.getCurrentUser().getUsername());
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        Mylog mylog = method.getAnnotation(Mylog.class);
//        System.out.println(mylog.description());//操作
//        System.out.println("method:"+method.getName());//调用的方法
//        System.out.println("args:"+joinPoint.getArgs());//参数
//        System.out.println("argsJSON"+ JSON.toJSONString(joinPoint.getArgs()));
//        System.out.println(IPUtils.getIpAddr(HttpContextUtils.getHttpServletRequest()));//调用者的ip

        String uname = ShiroUtils.getCurrentUser().getUsername();
        String opration = mylog.value();
        String methodName = joinPoint.getTarget().getClass()+"."+joinPoint.getSignature().getName();
        String params = JSON.toJSONString(joinPoint.getArgs());
        String ip = IPUtils.getIpAddr(HttpContextUtils.getHttpServletRequest());

        SysLog sysLog = new SysLog();
        sysLog.setCreateDate(new Date());
        sysLog.setIp(ip);
        sysLog.setMethod(methodName);
        sysLog.setParams(params);
        sysLog.setUsername(uname);
        sysLog.setOperation(opration);

        int i = sysLogService.saveLog(sysLog);
        System.out.print(i>0?"成功":"失败");

    }


}
