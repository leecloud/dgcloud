package com.lee.cloudbase.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.lee.cloudcommon.dto.LogDo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.mapstruct.Mapper;

@Mapper
public interface LogDao extends BaseMapper<LogDo> {

    //根据id查询
    LogDo getById(@Param("id") Long id);


}
