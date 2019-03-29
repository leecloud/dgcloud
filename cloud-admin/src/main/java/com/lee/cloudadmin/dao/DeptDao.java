package com.lee.cloudadmin.dao;

import com.lee.cloudadmin.domain.DeptDo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeptDao {
    DeptDo get(Long deptId);
}
