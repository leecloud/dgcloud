package com.lee.cloudcommon.intercepter;

import com.lee.cloudcommon.constants.CommonConstants;
import com.lee.cloudcommon.context.FilterContextHandler;
import com.lee.cloudcommon.dto.UserToken;
import com.lee.cloudcommon.utils.JWTUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthIntercepter extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(AuthIntercepter.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(CommonConstants.CONTEXT_TOKEN);
        UserToken userToken = JWTUtils.getInfoFromToken(token);
        FilterContextHandler.setToken(token);
        logger.info("----->设置token"+Thread.currentThread().getId());
        FilterContextHandler.setUserName(userToken.getUsername());
        FilterContextHandler.setName(userToken.getName());
        FilterContextHandler.setUserID(userToken.getUserId());
        return super.preHandle(request,response,handler);
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        FilterContextHandler.remove();
        super.afterCompletion(request,response,handler,ex);
    }
}
