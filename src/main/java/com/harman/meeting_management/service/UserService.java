package com.harman.meeting_management.service;

import com.harman.meeting_management.dto.UserDetailsDto;
import com.harman.meeting_management.entity.Admin;
import com.harman.meeting_management.entity.User;
import com.harman.meeting_management.entity.UserT;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

/**
 * @author L.Willian
 * @date 11/7/2019 11:28 AM
 */
public interface UserService {
    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    public int addUser(User user);

    /**
     * 通过邮箱查找用户
     *
     * @param email
     * @return
     */
    public User findByEmail(String email);

    /**
     * 通过员工号查找用户
     *
     * @param employeeId
     * @return
     */
    public User findByEmployeeId(String employeeId);

    /**
     * 修改登陆时间和ip登录
     *
     * @param user
     * @return
     */
    public int modifyPre(User user);

    /**
     * 通过部门id 来得到 员工的名字和 id
     *
     * @param departmentId
     * @return
     */
    public List<Map<String, Object>> findByDepartmentId(Long departmentId);

    /**
     * 通过用户名查询
     *
     * @param userName
     * @return
     */
    UserDetailsDto findByName(String userName);


    /**
     * 用户注册
     *
     * @param userParam
     * @return
     */
    UserT register(UserT userParam);

    /**
     * 登录功能
     *
     * @param username
     * @param password
     * @return
     */
    String login(String username, String password);

    /**
     * 刷新token
     *
     * @param oldToken
     * @return
     */
    String refreshToken(String oldToken);
}
