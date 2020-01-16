package com.harman.meeting_management.controller;

import com.harman.meeting_management.common.api.CommonResult;
import com.harman.meeting_management.common.api.ResultCode;
import com.harman.meeting_management.entity.Department;
import com.harman.meeting_management.entity.Room;
import com.harman.meeting_management.service.DepartmentService;
import com.harman.meeting_management.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author L.Willian
 * @date 11/12/2019 9:10 AM
 */
@RestController
public class AdminController {

    @Autowired
    public DepartmentService departmentService;

    @Autowired
    private RoomService roomService;

    // admin add department
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
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
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
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
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
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



    // 管理员添加会议室
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/admin/addRoom")
    public Map<String, Object> doAddRoom(@RequestParam("roomNum") String roomNum,
                                         @RequestParam("roomName") String roomName,
                                         @RequestParam("capacity") Integer personNum,
                                         @RequestParam("area") BigDecimal area,
                                         @RequestParam("discription") String discription) {
        Map<String, Object> map = new HashMap<>();
        Room roomDto = new Room();
        roomDto.setRoomNum(roomNum);
        roomDto.setRoomName(roomName);
        roomDto.setCapacity(personNum);
        roomDto.setArea(area);
        roomDto.setDiscription(discription);
        try {
            // 添加一条会议室
            int i = roomService.addRoom(roomDto);
            if (i == 1) {
                map.put("code", 200);
                map.put("msg", "添加成功");
            } else if (i == 0) {
                map.put("code", 210);
                map.put("msg", "添加失败");
            }
        } catch (DataAccessException e) {
            // catch mybatis throws exception
            Throwable cause = e.getCause();
            // Determine if it is the only constraint
            if (cause instanceof SQLIntegrityConstraintViolationException) {
                map.put("code", 210);
                map.put("error", cause.getMessage());
                map.put("msg", "添加失败，房间已被添加，具体请联系管理员");
            }
        }
        return map;
    }

    // 得到所有会议室房间信息
    @GetMapping("/roomAll")
    public Map<String, Object> doFindAllRoom() {
        Map<String, Object> map = new HashMap<>();
        //取得会议室房间信息数据
        List<Room> roomList = roomService.findAll();
        if (roomList == null || roomList.size() == 0) {
            map.put("code", 210);
            map.put("msg", "没有数据");
        } else {
            map.put("code", 200);
            map.put("msg", roomList);
        }
        return map;
    }

    //管理员修改会议室房间信息
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/admin/modifyRoomInfo")
    public Map<String, Object> doModifyRoom(@RequestParam("roomId") Long roomId,
                                            @RequestParam("roomName") String roomName,
                                            @RequestParam("capacity") Integer personNum,
                                            @RequestParam("area") BigDecimal area,
                                            @RequestParam("status") Integer status,
                                            @RequestParam("discription") String discription) {
        Map<String, Object> map = new HashMap<>();
        Room roomDto = new Room();
        roomDto.setId(roomId);
        roomDto.setRoomName(roomName);
        roomDto.setCapacity(personNum);
        roomDto.setArea(area);
        roomDto.setStatus(status);
        roomDto.setDiscription(discription);
        // 进行房间信息修改
        int i = roomService.modifyRoomInfo(roomDto);
        if (i == 1) {
            map.put("code", 200);
            map.put("msg", "修改成功");
        } else {
            map.put("code", 210);
            map.put("msg", "修改失败");
        }
        return map;
    }

    //管理员删除指定房间
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/admin/deleteRoom")
    public Map<String, Object> doDeleteRoom(@RequestParam("roomId") Long roomId) {
        Map<String, Object> map = new HashMap<>();
        // 进行删除
        int i = roomService.deleteByRoomId(roomId);
        if (i == 1) {
            map.put("code", 200);
            map.put("msg", "删除成功");
        } else {
            map.put("code", 210);
            map.put("msg", "删除失败");
        }
        return map;
    }

}
