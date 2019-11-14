package com.harman.meeting_management.controller;

import com.harman.meeting_management.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author L.Willian
 * @date 11/14/2019 10:26 AM
 */
@RestController
public class ReservationController {


    @PostMapping("/reserveMeeting")
    public Map<String, Object> doReserveMeeting(@RequestParam("meetingName") String meetingName,        //会议名字
                                                @RequestParam("reservePersonNum") Integer personNum,    //预计人数
                                                @RequestParam("preStarTime") String preStarTime,        //预计开始时间
                                                @RequestParam("preEndTime") String preEndTime,          //预计结束时间
                                                @RequestParam("roomId") Long roomId,
                                                @RequestParam("discription") String discription,        //会议描述
                                                @RequestParam("user_id") Long userId) {//通过部门来查询部门下的员工对应的userId

        Map<String, Object> map = new HashMap<>();


        return map;
    }

    @Autowired
    private RoomService roomService;

    @GetMapping("/getRoomName")//查询可用的会议房间
    public Map<String, Object> getRoomId() {
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> roomInfoAble = roomService.findRoomInfoAble();
        if (roomInfoAble == null || roomInfoAble.size() == 0) {
            map.put("code", 210);
            map.put("msg", "没有可用房间");
        } else {
            map.put("code", 200);
            map.put("msg", roomInfoAble);
        }
        return map;
    }

}
