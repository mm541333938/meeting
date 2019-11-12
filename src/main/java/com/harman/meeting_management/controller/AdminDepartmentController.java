package com.harman.meeting_management.controller;

import com.harman.meeting_management.entity.Department;
import com.harman.meeting_management.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author L.Willian
 * @date 11/12/2019 9:10 AM
 */
@RestController
public class AdminDepartmentController {

    @Autowired
    public DepartmentService departmentService;

    // admin add department
    @GetMapping("/admin/addDepartment")
    public Map<String, Object> doAddDepartment(@RequestParam("departmentName") String departName) {
        Map<String, Object> map = new HashMap<>();
        Department department = new Department();
        department.setName(departName);
        int i = departmentService.addDepartment(department);
        if (i == 1) {
            map.put("code", 200);
            map.put("msg", "添加成功");
        } else {
            map.put("code", 210);
            map.put("msg", "添加失败");
        }
        return map;
    }


}
