package com.at.ssm.crud.controller;

import com.at.ssm.crud.bean.EmployeeVo;
import com.at.ssm.crud.bean.ResultMsg;
import com.at.ssm.crud.service.EmpWithDeptService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 查询员工的处理器
 * @author Beyond
 * @date 2019/04/14
 */
@Controller
public class EmpWithDeptController {

    @Autowired
    EmpWithDeptService empWithDeptService;

    /**
     * 查询员工全部信息
     * @param pn 页数 默认值是1
     * @return 服务器处理后的信息
     */
    @ResponseBody
    @RequestMapping(value = "/emps", method = RequestMethod.GET)
    public ResultMsg getEmps(@RequestParam(value = "pn", defaultValue = "1") Integer pn){
        //设置分页
        PageHelper.startPage(pn, 10);
        // startPage后紧跟的查询就是分页查询
        List<EmployeeVo> emps = empWithDeptService.getAll();
        // 页面的全部信息
        PageInfo pageInfo = new PageInfo(emps, 5);
        // 返回服务器处理后的信息
        ResultMsg resultMsg = ResultMsg.success().addData("pageInfo", pageInfo);
        return resultMsg;
    }

    /**
     * 添加员工
     * @param employeeVo 员工
     * @return 消息
     */
    @ResponseBody
    @RequestMapping(value = "/emps", method = RequestMethod.POST)
    public ResultMsg saveEmp(EmployeeVo employeeVo){
        empWithDeptService.addEmpWithDept(employeeVo);
        return ResultMsg.success();
    }

    /**
     * 删除一个员工
     * @param id id
     * @return 返回消息
     */
    @ResponseBody
    @RequestMapping(value = "/emps/{id}", method = RequestMethod.DELETE)
    public ResultMsg deleteEmp(@PathVariable("id") Integer id ){
        empWithDeptService.deleteEmp(id);
        return ResultMsg.success();
    }

    /**
     * 更新信息
     * @param employeeVo 员工
     * @return 信息
     */
    @ResponseBody
    @RequestMapping(value = "/emps/{empId}", method = RequestMethod.PUT)
    public ResultMsg updateEmp(EmployeeVo employeeVo){
        empWithDeptService.updateEmp(employeeVo);
        return ResultMsg.success();
    }

    /**
     * 查询单个员工信息实现回显
     * @param id id
     * @return 单个员工信息
     */
    @ResponseBody
    @RequestMapping(value = "/emps/{id}", method = RequestMethod.GET)
    public ResultMsg getOneEmp(@PathVariable("id") Integer id){
        EmployeeVo oneEmp = empWithDeptService.getOneEmp(id);
        return ResultMsg.success().addData("emp", oneEmp);
    }
}

