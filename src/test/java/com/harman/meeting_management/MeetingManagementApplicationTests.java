package com.harman.meeting_management;

import com.harman.meeting_management.entity.Meeting;
import com.harman.meeting_management.mapper.MeetingMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MeetingManagementApplicationTests {

    @Autowired
    private MeetingMapper mapper;

   /* @Test
    void contextLoads() {
    }*/

    @Test
    public void insertMeeting() {
        Meeting meetingDto = new Meeting();
        meetingDto.setRoomId(3l);

        int i = mapper.insertSelective(meetingDto);
        System.out.println(i);
    }

}
