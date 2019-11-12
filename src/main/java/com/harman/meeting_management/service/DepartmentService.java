package com.harman.meeting_management.service;

import com.harman.meeting_management.entity.Department;

/**
 * @author L.Willian
 * @date 11/11/2019 4:04 PM
 */
public interface DepartmentService {

    /**
     * 添加部门信息
     *
     * @param department
     * @return
     */
    public int addDepartment(Department department);
}
