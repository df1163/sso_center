package com.hsjc.ssoCenter.core.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.hsjc.ssoCenter.core.annotation.SSOSystemLog;
import com.hsjc.ssoCenter.core.mapper.RestfulLogMapper;
import com.hsjc.ssoCenter.core.mapper.SystemLogMapper;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : zga
 * @date : 2015-12-14
 *
 * 日志拦截器
 */
@SuppressWarnings("ALL")
@Component
@Aspect
public class LogInterCeptor {
    private Logger logger = Logger.getLogger(LogInterCeptor.class);

    @Autowired
    private RestfulLogMapper restfulLogMapper;

    @Autowired
    private SystemLogMapper systemLogMapper;

    @Pointcut("@annotation(com.hsjc.ssoCenter.core.annotation.SSOSystemLog)")
    public  void controllerAspect() {
    }

    /**
     * @author : zga
     * @date : 2015-12-14
     *
     * 操作异常记录
     *
     * @param point
     * @param e
     */
    @AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
    public  void doAfterThrowing(JoinPoint point, Throwable e) {

    }

    @After("controllerAspect()")
    public void doAroundInvoke(JoinPoint joinPoint){
        /**
         * 获取保存日志的信息
         */
        Map<String,Object> map = null;
        try {
            map = getMethodDescription(joinPoint);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * 保存日志
         */
        //
        Object object = map.get("clientId");
        if(object == null){
            //系统日志保存
            com.hsjc.ssoCenter.core.domain.SystemLog systemLog = new com.hsjc.ssoCenter.core.domain.SystemLog();
            systemLog.setCreateDate(new Date());
            systemLog.setLoginTime(new Date());
            systemLog.setLoginIp((map.get("host") == null ? "" : map.get("host").toString()));
            systemLog.setLoginType("1");
            systemLog.setUserId(map.get("userName") == null ? "" : map.get("userName").toString());

            systemLogMapper.insert(systemLog);

        } else {
            //同步接口日志保存
            com.hsjc.ssoCenter.core.domain.RestfulLog restfulLog = new com.hsjc.ssoCenter.core.domain.RestfulLog();
            restfulLog.setClientId(map.get("clientId").toString());
            restfulLog.setActionId(Integer.parseInt(map.get("actionId").toString()));
            restfulLog.setActionTime(new Date());
            restfulLog.setDescription(map.get("description").toString());

            restfulLogMapper.insert(restfulLog);

            /**
             * 返回数据中插入requestSynId字段
             */
            Object target = (joinPoint == null ? null : joinPoint.getTarget());
            Signature signature = (joinPoint == null ? null : joinPoint.getSignature());

            if (target == null) return;
            if (signature == null) return;

            JSONObject resultJson = null;
            Object[] args = joinPoint.getArgs();
            for(Object o : args){
                if(o instanceof JSONObject){
                    resultJson = (JSONObject)o;
                }
            }

            resultJson.put("requestSynId",restfulLog.getRestLogId());
        }
    }

    /**
     *
     * @author : zga
     * @date : 2015-12-14
     *
     * 获取
     *
     * @param joinPoint
     * @return
     * @throws Exception
     */
    public Map<String, Object> getMethodDescription(JoinPoint joinPoint)  throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        JSONObject paramJson = null;
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    map.put("actionId", method.getAnnotation(SSOSystemLog.class).actionId());
                    if(arguments[0] instanceof JSONObject){
                        paramJson = (JSONObject)arguments[0];
                        map.put("clientId",paramJson.getString("clientId"));
                    } else if(arguments[0] instanceof HttpServletRequest){
                        HttpServletRequest request = (HttpServletRequest)arguments[0];
                        String host = request.getRemoteHost();
                        System.out.println("host is >>" + host);

                        map.put("host",host);
                        map.put("userName",arguments[1]);
                    }
                    String de = method.getAnnotation(SSOSystemLog.class).description();
                    if(StringUtils.isEmpty(de)) de = method.getAnnotation(SSOSystemLog.class).module() + ","
                            + method.getAnnotation(SSOSystemLog.class).description()
                            +"同步成功!";
                    map.put("description", de);
                    break;
                }
            }
        }
        return map;
    }
}
