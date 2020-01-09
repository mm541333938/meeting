package com.harman.meeting_management.controller;

import com.harman.meeting_management.common.api.CommonResult;
import com.harman.meeting_management.common.api.ResultCode;
import com.harman.meeting_management.entity.Department;
import com.harman.meeting_management.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
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
    public CommonResult doAddDepartment(@RequestParam("departmentName") String departName) {
        //Map<String, Object> map = new HashMap<>();
        Department department = new Department();
        department.setName(departName);
        try {
            int i = departmentService.addDepartment(department);
            if (i == 1) {
                return CommonResult.success(null);
            } else {
                return CommonResult.failed(ResultCode.FAILED2.getCode(), ResultCode.FAILED2.getMessage());
            }
        } catch (DataAccessException e) {
            // catch mybatis throws exception
            Throwable cause = e.getCause();
            // Determine if it is the only constraint
            if (cause instanceof SQLIntegrityConstraintViolationException) {
               /* map.put("code", 210);
                map.put("error", cause.getMessage());
                map.put("msg", "添加失败，部门名称已存在");*/
                return CommonResult.failed("添加失败，部门名称已存在");
            }
        }
        return null;
    }

    //管理员修改部门信息
    @GetMapping("/admin/modifyDepartment")
    public Map<String, Object> doModifyDepartment(@RequestParam("departName") String departName,
                                                  @RequestParam("department_id") Long id) {
        Map<String, Object> map = new HashMap<>();
        Department department = new Department();
        department.setId(id);
        department.setName(departName);
        //进行修改部门信息
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

    //管理员删除部门
    @DeleteMapping("/admin/deleteDepartment")
    public Map<String, Object> doDeleteDepartment(@RequestParam("department_id") Long id) {
        Map<String, Object> map = new HashMap<>();
        //进行删除
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

    //得到部门数据
    @GetMapping("/department")
    public Map<String, Object> getListDepartment() {
        Map<String, Object> map = new HashMap<>();
        //取得部门信息
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
