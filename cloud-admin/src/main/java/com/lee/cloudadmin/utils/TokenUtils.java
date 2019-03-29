package com.lee.cloudadmin.utils;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class TokenUtils {

    public String getCurrent(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies){
            if ("token".equals(cookie.getName())){
                return cookie.getValue();
            }
        }
        return null;
    }
}
