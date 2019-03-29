package com.lee.cloudbase.sevice;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.lee.cloudcommon.dto.LogDo;
import com.lee.cloudcommon.utils.PageUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface LogService {
    LogDo get(Long id);
}
