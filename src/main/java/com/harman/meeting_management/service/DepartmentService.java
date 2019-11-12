package com.harman.meeting_management.service;

import com.harman.meeting_management.entity.Department;

import java.util.List;

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

    /**
     * 修改部门信息，通过id
     *
     * @param department
     * @return
     */
    public int modifyDepartment(Department department);

    /**
     * 通过id 删除指定部门
     *
     * @param id
     * @return
     */
    public int deleteById(Long id);

    /**
     * 查询所有部门
     *
     * @return
     */
    public List<Department> findAll();


}
