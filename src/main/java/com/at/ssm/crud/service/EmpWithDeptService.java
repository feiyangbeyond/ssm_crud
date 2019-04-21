package com.at.ssm.crud.service;

import com.at.ssm.crud.bean.EmployeeVo;

import java.util.List;

/**
 * @author Beyond
 * @date 2019/04/14
 */
public interface EmpWithDeptService {

    /**
     *  查询全部员工
     * @return 员工集合
     */
    List<EmployeeVo> getAll();

    /**
     *  添加员工信息+部门
     * @param employeeVo emp
     */
    void addEmpWithDept(EmployeeVo employeeVo);

    /**
     * 删除用户
     * @param id id
     */
    void deleteEmp(Integer id);

    /**
     * 更新员工
     * @param employeeVo emp
     */
    void updateEmp(EmployeeVo employeeVo);

    /**
     * 查询单个员工
     * @param id id
     * @return 单个员工
     */
    EmployeeVo getOneEmp(Integer id);
}
