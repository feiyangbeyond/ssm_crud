package com.at.ssm.crud.service.impl;

import com.at.ssm.crud.bean.EmployeeVo;
import com.at.ssm.crud.dao.EmpWithDeptMapper;
import com.at.ssm.crud.dao.EmployeeMapper;
import com.at.ssm.crud.service.EmpWithDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Beyond
 * @date 2019/04/14
 */
@Service
public class EmpWithDeptServiceImpl implements EmpWithDeptService {

    @Autowired
    private EmpWithDeptMapper empWithDeptMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeVo> getAll() {
        return empWithDeptMapper.selectByExampleWithDept(null);
    }

    @Override
    public void addEmpWithDept(EmployeeVo employeeVo) {
        employeeMapper.insertSelective(employeeVo);
    }

    @Override
    public void deleteEmp(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateEmp(EmployeeVo employeeVo) {
        employeeMapper.updateByPrimaryKey(employeeVo);
    }

    @Override
    public EmployeeVo getOneEmp(Integer id) {
        return empWithDeptMapper.selectByPrimaryKeyWithDept(id);
    }
}
