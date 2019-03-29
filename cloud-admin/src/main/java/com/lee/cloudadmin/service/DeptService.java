package com.lee.cloudadmin.service;

import com.lee.cloudadmin.domain.DeptDo;
import org.springframework.stereotype.Service;

public interface DeptService {
    DeptDo getById(Long deptId);
}
