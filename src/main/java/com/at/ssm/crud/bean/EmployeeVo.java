package com.at.ssm.crud.bean;

/**
 * @author Beyond
 * @date 2019/04/13
 */
public class EmployeeVo extends Employee {

    private Department department;

    public EmployeeVo() {
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public EmployeeVo(Department department) {
        this.department = department;
    }
}
