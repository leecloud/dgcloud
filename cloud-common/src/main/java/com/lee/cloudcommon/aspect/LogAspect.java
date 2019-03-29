package com.lee.cloudcommon.aspect;

import com.lee.cloudcommon.annotation.Log;
import com.lee.cloudcommon.context.FilterContextHandler;
import com.lee.cloudcommon.dto.LogDo;
import com.lee.cloudcommon.service.LogRpcService;
import com.lee.cloudcommon.utils.HttpContextUtils;
import com.lee.cloudcommon.utils.IPUtils;
import com.lee.cloudcommon.utils.JsonUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 系统日志切面
 */
@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
   @Autowired
   LogRpcService logRpcService;

    @Pointcut("@annotation(com.lee.cloudcommon.annotation.Log)")
    public void logPointCut(){}
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长
        long time = System.currentTimeMillis() - beginTime;
        saveLog(point,time);
        return result;
    }

    private void saveLog(ProceedingJoinPoint point, long time) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        LogDo sysLog = new LogDo();
        Log annotation = method.getAnnotation(Log.class);
        if (sysLog != null){
            sysLog.setOperation(annotation.value());
        }
        String className = point.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className+"."+methodName+"()");
        Object[] args = point.getArgs();
        try {
            String params = JsonUtils.beanToJson(args[0].toString().substring(0, 4999));
            sysLog.setParams(params);
        }catch (Exception e){

        }
        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //获取ip
        String ipAddr = IPUtils.getIpAddr(request);
        sysLog.setIp(ipAddr);
        //用户名
        sysLog.setUserId(Long.parseLong(FilterContextHandler.getUserID() == null ? "000000" : FilterContextHandler.getUserID()));
        sysLog.setUsername(FilterContextHandler.getUsername() == null ? "" : FilterContextHandler.getUsername());
        sysLog.setTime((int) time);
        Date date = new Date();
        sysLog.setGmtCreate(date);
        logRpcService.save(sysLog);
    }
}
