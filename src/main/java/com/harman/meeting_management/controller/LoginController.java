package com.harman.meeting_management.controller;

import com.harman.meeting_management.common.HttpUtil;
import com.harman.meeting_management.entity.Admin;
import com.harman.meeting_management.entity.User;
import com.harman.meeting_management.service.AdminService;
import com.harman.meeting_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author L.Willian
 * @date 11/1/2019 2:17 PM
 */

@RestController
public class LoginController extends HttpUtil {
    @Autowired
    private AdminService adminService;

    // admin account sign in
    @PostMapping("/admin/login")
    public Map<String, Object> doAdminLogin(@RequestParam("username") String userName,
                                            @RequestParam("password") String pwd) {
        Map<String, Object> map = new HashMap<>();
        //通过用户名获取到用户对象
        Admin adminDto = adminService.findByName(userName);
        if (pwd.trim().equals(adminDto.getPassword().trim())) {
            map.put("code", 200);
            map.put("msg", "登陆成功");
        } else {
            map.put("code", 210);
            map.put("msg", "密码不正确");
        }
        //密码加密对比处理
        return map;
    }

    @Autowired
    private UserService userService;

    // normal account sign in
    @PostMapping("/login")
    public Map<String, Object> doLogin(@RequestParam("user_name") String userName,
                                       @RequestParam("password") String password) {
        Map<String, Object> map = new HashMap<>();

        //TODO 可用多线程
        User byEmployeeId = userService.findByEmployeeId(userName);
        User byEmail = userService.findByEmail(userName);
        //-----------------------------------------------------------
        if (byEmail != null || byEmployeeId != null){
            map.put("code",200);
            map.put("msg","登录成功");
        } else {
            map.put("code",210);
            map.put("msg","账号未注册");
        }
        return map;
    }


}
