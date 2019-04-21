package com.at.ssm.crud.controller;

import com.at.ssm.crud.bean.Department;
import com.at.ssm.crud.bean.ResultMsg;
import com.at.ssm.crud.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 处理部门有关的请求
 * @author Beyond
 * @date 2019/04/20
 */
@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 查询所有部门
     * @return all部门
     */
    @RequestMapping("/getDepts")
    @ResponseBody
    public ResultMsg getDept(){
        List<Department> depts = departmentService.getDepts();
        return ResultMsg.success().addData("depts", depts);
    }


}
