package com.lee.cloudcommon.service;

import com.lee.cloudcommon.dto.LogDo;
import com.lee.cloudcommon.intercepter.FeignIntercepter;
import com.lee.cloudcommon.utils.R;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;

@Headers("Content-Type:application/json")
@FeignClient(name = "api-base",configuration = FeignIntercepter.class)
public interface LogRpcService {
    /**
     * 保存系统日志
     */
    @Async
    @PostMapping("log/save")
    R save(LogDo logDo);
}
