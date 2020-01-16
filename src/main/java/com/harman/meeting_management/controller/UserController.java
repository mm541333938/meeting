package com.harman.meeting_management.controller;

import com.harman.meeting_management.common.util.HttpUtil;
import com.harman.meeting_management.entity.Admin;
import com.harman.meeting_management.entity.User;
import com.harman.meeting_management.service.AdminService;
import com.harman.meeting_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author L.Willian
 * @date 2020/1/16
 */
@RestController
public class UserController extends HttpUtil {

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
    @Transactional
    @PostMapping("/login")
    public Map<String, Object> doLogin(@RequestParam("user_name") String userName,
                                       @RequestParam("password") String password) {
        Map<String, Object> map = new HashMap<>();
        User user = new User();
        //TODO 可用多线程
        User byEmployeeId = userService.findByEmployeeId(userName);
        User byEmail = userService.findByEmail(userName);
        //-----------------------------------------------------------
        if (byEmail != null || byEmployeeId != null) {
            String pwd = byEmail == null ? byEmployeeId.getPassword() : byEmail.getPassword();
            if (pwd.trim().equals(password.trim())) {

                map.put("code", 200);
                map.put("msg", "登录成功");
                map.put("preTime", byEmail == null ? byEmployeeId.getPreTime() : byEmail.getPreTime());
                map.put("preIp", byEmail == null ? byEmployeeId.getpreIp() : byEmail.getpreIp());
                //登录成功之后更新数据
                user.setPreTime(new Date());
                user.setpreIp(getClientIpAddress());
                user.setId(byEmail == null ? byEmployeeId.getId() : byEmail.getId());

                userService.modifyPre(user);
            }
        } else {
            map.put("code", 210);
            map.put("msg", "账号未注册");
        }
        return map;
    }


    // to do register
    @PostMapping("/register")
    public Map<String, Object> doRegister(@RequestParam("email") String email,
                                          @RequestParam("password") String password,
                                          @RequestParam("name") String name,
                                          @RequestParam("employee_id") String employee_id,
                                          @RequestParam("phone") String phone,
                                          @RequestParam("department_id") Long departmentId) {
        Map<String, Object> map = new HashMap<>();
        // set User object
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        user.setPhone(phone);
        user.setStatus(1);
        user.setEmployeeId(employee_id);
        user.setDepartmentId(departmentId);

        try {
            // insert into mysql
            int insertNum = userService.addUser(user);
            if (insertNum == 1) {
                map.put("code", 200);
                map.put("msg", "注册成功");
            }
        } catch (DataAccessException e) {
            // catch mybatis throws exception
            Throwable cause = e.getCause();
            // Determine if it is the only constraint
            if (cause instanceof SQLIntegrityConstraintViolationException) {
                map.put("code", 210);
                map.put("error", cause.getMessage());
                map.put("msg", "注册失败，账号已存在或员工号已存在，具体请联系管理员");
            }
        }
        return map;
    }

    //Check if email is registered
    @GetMapping("/checkEmail")
    public Map<String, Object> doCheckEmail(@RequestParam("email") String email) {
        Map<String, Object> map = new HashMap<>();
        // if it is registered by email
        User byEmail = userService.findByEmail(email);
        if (byEmail == null) {
            map.put("code", 200);
            map.put("msg", "邮箱可用");
        } else {
            map.put("code", 210);
            map.put("msg", "邮箱已存在");
        }
        return map;
    }

    // check if employeeId is registered
    @GetMapping("/checkEmployee")
    public Map<String, Object> doCheckEmployeeId(@RequestParam("employee_id") String employee_id) {
        Map<String, Object> map = new HashMap<>();
        // if it is registered by employee id
        User byEmployeeId = userService.findByEmployeeId(employee_id);
        if (byEmployeeId == null) {
            map.put("code", 200);
            map.put("msg", "员工号可用");
        } else {
            map.put("code", 210);
            map.put("msg", "员工号已注册");
        }
        return map;
    }




}
