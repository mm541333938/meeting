package com.harman.meeting_management.controller;

import com.harman.meeting_management.entity.Meeting;
import com.harman.meeting_management.service.MeetingRUserService;
import com.harman.meeting_management.service.MeetingService;
import com.harman.meeting_management.service.RoomService;
import com.harman.meeting_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author L.Willian
 * @date 11/14/2019 10:26 AM
 */
@RestController
public class ReservationController {

    @Autowired
    private UserService userService;

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private MeetingRUserService meetingRUserService;

    //进行会议预约，并且根据部门来选择参加会议的人
    @PostMapping("/reserveMeeting")
    public Map<String, Object> doReserveMeeting(@RequestParam("meetingName") String meetingName,        //会议名字
                                                @RequestParam("reservePersonNum") Integer personNum,    //预计人数
                                                @RequestParam("preStarTime") Date preStarTime,        //预计开始时间
                                                @RequestParam("preEndTime") Date preEndTime,          //预计结束时间
                                                @RequestParam("roomId") Long roomId,
                                                @RequestParam("discription") String discription,        //会议描述
                                                @RequestParam("uIds") String[] userIds) {//通过部门来查询部门下的员工对应的userId
        //创建map作为返回值
        Map<String, Object> map = new HashMap<>();
        //定义一个meetingDto 为即将插入得数据进行封装
        Meeting meetingDto = new Meeting();
        meetingDto.setMeetingName(meetingName);
        meetingDto.setPrePersonNum(personNum);
        meetingDto.setStartTime(preStarTime);
        meetingDto.setEndTime(preEndTime);
        meetingDto.setRoomId(roomId);
        meetingDto.setDescription(discription);
        meetingDto.setReserveTime(new Date());//获取当前时间
        meetingDto.setDescription(discription);
        int i = meetingService.addMeeting(meetingDto);//插入数据
        Long currentDataId = meetingDto.getId();//取得刚插入的数据的id
        if (i == 1 && (currentDataId != null || currentDataId != 0)) {
            //执行往manytomany表里插入数据
            int num = meetingRUserService.addMeetingPerson(currentDataId, userIds);
            if (num > 0) {
                map.put("code", 200);
                map.put("msg", "预约成功");
            } else {
                map.put("code", 210);
                map.put("msg", "预约失败");
            }
        }
        return map;
    }

    @Autowired
    private RoomService roomService;

    //返回空闲可用的会议室房间信息
    @GetMapping("/getRoomName")//查询可用的会议房间
    public Map<String, Object> getRoomId() {
        // 声明返回值对象map
        Map<String, Object> map = new HashMap<>();
        //查询所有空闲房间status = 0的房间信息
        List<Map<String, Object>> roomInfoAble = roomService.findRoomInfoAble();
        // 如果list 为null 或者 大小 是 0 ，代表没查到对应的数据
        if (roomInfoAble == null || roomInfoAble.size() == 0) {
            map.put("code", 210);
            map.put("msg", "没有可用房间");
        } else {
            map.put("code", 200);
            map.put("msg", roomInfoAble);
        }
        return map;
    }

    //通过下拉按钮，来传过来对应的department_id，从而查询对应部门下的人
    @GetMapping("/getPersonName")
    public Map<String, Object> getPersonName(@RequestParam("department_id") Long departmentId) {
        Map<String, Object> map = new HashMap<>();
        //得到对应部门内的员工姓名 和column id 信息
        List<Map<String, Object>> InfoList = userService.findByDepartmentId(departmentId);
        if (InfoList == null || InfoList.size() == 0) {
            map.put("code", 210);
            map.put("msg", "没有信息");
        } else {
            map.put("code", 200);
            map.put("msg", InfoList);
        }
        return map;
    }

    //预约会议的退订
    @PostMapping("/canceledMeeting")
    public Map<String, Object> doCanceledMeeting(@RequestParam("meeting_id") Long meetingId,
                                                 @RequestParam("reason") String reason) {
        Map<String, Object> map = new HashMap<>();
        Meeting meetingDto = new Meeting();
        meetingDto.setId(meetingId);//取消会议的id
        meetingDto.setCanceledReason(reason);//取消会议的原因
        meetingDto.setStatus(-1);// -1 为取消的会议，0 预约的会议，1 已经开始或开过的会议
        meetingDto.setCanceledTime(new Date());//取消会议的时间，获取当前时间
        int i = meetingService.cancelMeeting(meetingDto);
        if (i == 1) {
            //取消成功
            map.put("code", 200);
            map.put("msg", "取消成功");
        } else {
            map.put("code", 210);
            map.put("msg", "取消失败请联系管理员");
        }
        return map;
    }
}
