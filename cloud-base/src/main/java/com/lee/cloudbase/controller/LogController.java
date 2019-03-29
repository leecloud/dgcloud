package com.lee.cloudbase.controller;

import com.lee.cloudbase.sevice.LogService;
import com.lee.cloudcommon.dto.LogDo;
import com.lee.cloudcommon.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统日志
 */
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    LogService logService;

    @GetMapping("/get/{id}")
    public R get(@PathVariable("id") Long id){
        LogDo logDo = logService.get(id);
        return R.ok().put("data",logDo);
    }
}
