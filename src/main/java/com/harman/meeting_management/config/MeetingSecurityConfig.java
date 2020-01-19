package com.harman.meeting_management.config;

import com.harman.meeting_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author L.Willian
 * @date 2020/1/19
 */
public class MeetingSecurityConfig extends SecurityConfig {

    @Autowired
    private UserService userService;

    @Bean
    public UserDetailsService userDetailsService(){
        //获取登录用户信息
        return username -> userService.loadUserByUsername(username);
    }
}
