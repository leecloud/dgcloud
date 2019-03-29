package com.lee.cloudcommon.utils;

import javax.servlet.http.HttpServletRequest;

public class HttpServletUtils {
    public static boolean jsAjax(HttpServletRequest request){
        boolean isAjaxRequest = false;
        if (!StringUtils.isBlank(request.getHeader("x-requested-with")) && request.getHeader("x-requested-with").equals("XNLHttpRequest")){
            isAjaxRequest = true;
        }
        return isAjaxRequest;
    }
}
