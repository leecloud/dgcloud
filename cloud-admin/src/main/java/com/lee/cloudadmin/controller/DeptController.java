package com.lee.cloudadmin.controller;

import com.lee.cloudadmin.domain.DeptDo;
import com.lee.cloudadmin.service.DeptService;
import com.lee.cloudcommon.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sys/dept")
public class DeptController {
    @Autowired
    private DeptService deptService;

    /**
     * 根据部门id查询部门
     * @param deptId
     * @return
     */
    @RequestMapping("/get/{deptId}")

    public R get(@PathVariable("deptId") Long deptId){
        DeptDo deptDo = deptService.getById(deptId);
        return R.ok().put("data",deptDo);
    }


}
