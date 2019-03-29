package com.lee.cloudzuul.rpc;

import com.lee.cloudcommon.dto.MenuDo;
import com.lee.cloudcommon.intercepter.FeignIntercepter;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Headers("Content-Type:application/json")
@FeignClient(name = "api-admin",configuration = FeignIntercepter.class)
public interface MenuService {
    @GetMapping("/menu/userMenus")
    List<MenuDo> userMeuns();
}
