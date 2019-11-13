package com.harman.meeting_management.controller;

import com.harman.meeting_management.entity.Department;
import com.harman.meeting_management.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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


    @GetMapping("/admin/modifyDepartment")
    public Map<String, Object> doModifyDepartment(@RequestParam("departName") String departName,
                                                  @RequestParam("department_id") Long id) {
        Map<String, Object> map = new HashMap<>();
        Department department = new Department();
        department.setId(id);
        department.setName(departName);
        int i = departmentService.modifyDepartment(department);
        if (i == 1) {
            map.put("code", 200);
            map.put("msg", "修改成功");
        } else {
            map.put("code", 210);
            map.put("msg", "修改失败");
        }
        return map;
    }

    @DeleteMapping("/admin/deleteDepartment")
    public Map<String, Object> doDeleteDepartment(@RequestParam("department_id") Long id) {
        Map<String, Object> map = new HashMap<>();
        int i = departmentService.deleteById(id);
        if (i == 1) {
            map.put("code", 200);
            map.put("msg", "删除成功");
        } else {
            map.put("code", 210);
            map.put("msg", "删除失败");
        }
        return map;
    }

    @GetMapping("/department")
    public Map<String, Object> getListDepartment() {
        Map<String, Object> map = new HashMap<>();
        List<Department> all = departmentService.findAll();
        if (all.size() != 0 || all != null) {
            map.put("code", 200);
            map.put("msg", all);
        } else {
            map.put("code", 210);
            map.put("msg", "没有部门数据");
        }
        return map;
    }

}
