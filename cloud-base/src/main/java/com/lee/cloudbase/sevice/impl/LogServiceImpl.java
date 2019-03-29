package com.lee.cloudbase.sevice.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.lee.cloudbase.dao.LogDao;
import com.lee.cloudbase.sevice.LogService;
import com.lee.cloudcommon.dto.LogDo;
import com.lee.cloudcommon.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogDao logDao;


    @Override
    public LogDo get(Long id) {
        //return null;
        return logDao.getById(id);
    }
}
