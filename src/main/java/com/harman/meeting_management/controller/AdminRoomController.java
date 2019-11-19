package com.harman.meeting_management.controller;

import com.harman.meeting_management.entity.Room;
import com.harman.meeting_management.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author L.Willian
 * @date 11/13/2019 2:14 PM
 */
@RestController
public class AdminRoomController {

    @Autowired
    private RoomService roomService;

    // 管理员添加会议室
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
