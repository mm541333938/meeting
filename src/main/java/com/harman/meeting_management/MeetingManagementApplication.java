package com.harman.meeting_management;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.harman.meeting_management.mapper","com.harman.meeting_management.dao"}) //扫描mapper接口位置
public class MeetingManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(MeetingManagementApplication.class, args);
    }
}
