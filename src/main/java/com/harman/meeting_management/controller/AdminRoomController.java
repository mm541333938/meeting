package com.harman.meeting_management.controller;

import com.harman.meeting_management.entity.Room;
import com.harman.meeting_management.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author L.Willian
 * @date 11/13/2019 2:14 PM
 */
public class AdminRoomController {

    @Autowired
    private RoomService roomService;

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

}
