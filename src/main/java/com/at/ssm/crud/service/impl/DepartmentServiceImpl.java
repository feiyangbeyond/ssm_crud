package com.at.ssm.crud.service.impl;

import com.at.ssm.crud.bean.Department;
import com.at.ssm.crud.dao.DepartmentMapper;
import com.at.ssm.crud.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Beyond
 * @date 2019/04/20
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;


    @Override
    public List<Department> getDepts() {
        List<Department> departments = departmentMapper.selectByExample(null);
        return departments;
    }
}
