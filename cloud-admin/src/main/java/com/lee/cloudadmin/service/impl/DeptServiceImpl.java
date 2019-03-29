package com.lee.cloudadmin.service.impl;

import com.lee.cloudadmin.dao.DeptDao;
import com.lee.cloudadmin.domain.DeptDo;
import com.lee.cloudadmin.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptDao deptDao;
    @Override
    public DeptDo getById(Long deptId) {
        return deptDao.get(deptId);
    }
}
