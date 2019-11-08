package com.harman.meeting_management.controller;

import com.harman.meeting_management.common.HttpUtil;
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

    @PostMapping("/admin/login")
    public Map<String, Object> doLogin(@RequestParam("username") String userName,
                                       @RequestParam("password") String pwd) {
        Map<String, Object> map = new HashMap<>();
        //通过用户名获取到用户对象

        //密码加密对比处理
        map.put("code", 200);
        map.put("msg", "");
        return map;
    }


}
