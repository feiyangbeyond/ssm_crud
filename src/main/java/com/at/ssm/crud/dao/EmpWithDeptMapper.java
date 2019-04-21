package com.at.ssm.crud.dao;

import com.at.ssm.crud.bean.EmployeeExample;
import com.at.ssm.crud.bean.EmployeeVo;

import java.util.List;

/**
 * @author Beyond
 * @date 2019/04/13
 */
public interface EmpWithDeptMapper {
    /**
     * 查询员工 带有部门信息
     * @param example employee
     * @return list
     */
    List<EmployeeVo> selectByExampleWithDept(EmployeeExample example);

    /**
     * 主键查询员工
     * @param empId id
     * @return employee
     */
    EmployeeVo selectByPrimaryKeyWithDept(Integer empId);
}
