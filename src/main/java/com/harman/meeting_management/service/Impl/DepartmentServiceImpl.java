package com.harman.meeting_management.service.Impl;

import com.harman.meeting_management.entity.Department;
import com.harman.meeting_management.mapper.DepartmentMapper;
import com.harman.meeting_management.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author L.Willian
 * @date 11/12/2019 9:19 AM
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    public DepartmentMapper departmentMapper;

    public int addDepartment(Department department) {
        return departmentMapper.insert(department);
    }

    public int modifyDepartment(Department department) {
        return departmentMapper.updateByPrimaryKeySelective(department);
    }

    @Override
    public int deleteById(Long id) {
        return departmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Department> findAll() {
        return departmentMapper.select();
    }
}
