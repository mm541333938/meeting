package com.harman.meeting_management.service;

import com.harman.meeting_management.entity.User;

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

}
