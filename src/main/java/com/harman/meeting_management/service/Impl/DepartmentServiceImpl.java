package com.harman.meeting_management.service.Impl;

import com.harman.meeting_management.entity.Department;
import com.harman.meeting_management.mapper.DepartmentMapper;
import com.harman.meeting_management.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author L.Willian
 * @date 11/12/2019 9:19 AM
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    public DepartmentMapper departmentMapper;

    @Override
    public int addDepartment(Department department) {
        return departmentMapper.insert(department);
    }
}
