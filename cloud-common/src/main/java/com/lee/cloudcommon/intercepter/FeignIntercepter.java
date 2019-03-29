package com.lee.cloudcommon.intercepter;

import com.lee.cloudcommon.constants.CommonConstants;
import com.lee.cloudcommon.context.FilterContextHandler;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeignIntercepter implements RequestInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(FeignIntercepter.class);
    @Override
    public void apply(RequestTemplate requestTemplate) {
        logger.info("---->feign设置token"+Thread.currentThread().getId());
        requestTemplate.header(CommonConstants.CONTEXT_TOKEN, FilterContextHandler.getToken());
    }
}
