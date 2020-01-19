package com.harman.meeting_management;

import com.harman.meeting_management.dao.UserRoleDao;
import com.harman.meeting_management.dto.MeetingResultParam;
import com.harman.meeting_management.dto.UserDetailsDto;
import com.harman.meeting_management.entity.Role;
import com.harman.meeting_management.mapper.MeetingRUserMapper;
import com.harman.meeting_management.mapper.UserMapper;
import com.harman.meeting_management.service.MeetingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MeetingManagementApplicationTests {

    /*@Autowired
    private MeetingMapper mapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void insertMeeting() {
        Meeting meetingDto = new Meeting();
        meetingDto.setRoomId(3l);
        int i = mapper.insertSelective(meetingDto);
        System.out.println(meetingDto.getId());
    }*/

    @Autowired
    private MeetingRUserMapper meetingRUserMapper;

    @Autowired
    private MeetingService meetingService;

    @Test
    public void insertTest() {
        int i = meetingRUserMapper.insertArrUserId(2l, new String[]{"1", "2", "3"});
        System.out.println(i);
    }

    @Test
    public void selectSelfMeeting() {

        List<MeetingResultParam> result = meetingService.getMyMeeting(1l);
        System.out.println(result.get(0));

    }

    @Test
    public void selectCurrent7Days() {
        List<MeetingResultParam> result = meetingService.getMeeting7Days(1l);
        System.out.println(result.get(0));

    }

    @Test
    public void selectCancelMeetingByUid() {
        List<MeetingResultParam> result = meetingService.getCanceledMeeting(1l);
        System.out.println(result.get(0));
    }


    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectByUsername() {
        List<UserDetailsDto> admin = userMapper.selectByUsername("admin");
        System.out.println(admin);
    }

    @Autowired
    private UserRoleDao userRoleDao;

    @Test
    public void getRoleList() {
        List<Role> roleList = userRoleDao.getRoleList(1l);
        for (Role r : roleList) {
            System.out.println(r.toString());
        }
    }


}
