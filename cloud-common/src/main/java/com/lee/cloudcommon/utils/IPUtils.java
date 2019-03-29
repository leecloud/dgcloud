package com.lee.cloudcommon.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class IPUtils {
    private static Logger logger = LoggerFactory.getLogger(IPUtils.class);
    /**
     * 获取ip地址
     */

    public static String getIpAddr(HttpServletRequest request){
        String header = request.getHeader("x-forwarded-for");
        if (header == null || header.length() == 0 || "unknow".equalsIgnoreCase(header)){
            header = request.getHeader("Proxy-Client-IP");
        }
        if (header == null || header.length() == 0 || "unknow".equalsIgnoreCase(header)){
            header = request.getHeader("WL-Proxy-Client-IP");
        }
        if (header == null || header.length() == 0 || "unknow".equalsIgnoreCase(header)){
            header = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(header) ? "127.0.0.1" : header;
    }
}
