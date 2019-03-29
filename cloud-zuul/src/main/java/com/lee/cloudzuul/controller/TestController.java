package com.lee.cloudzuul.controller;

import com.lee.cloudcommon.constants.CommonConstants;
import com.lee.cloudcommon.context.FilterContextHandler;
import com.lee.cloudcommon.dto.MenuDo;
import com.lee.cloudzuul.rpc.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 测试
 */
@RestController
public class TestController {
    @Autowired
    MenuService menuService;

    @GetMapping("/test")
    List<MenuDo> login(HttpServletRequest request){
        FilterContextHandler.setToken(request.getHeader(CommonConstants.CONTEXT_TOKEN));
        return menuService.userMeuns();
    }
}
