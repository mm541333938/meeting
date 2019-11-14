package com.harman.meeting_management.service.Impl;

import com.harman.meeting_management.entity.User;
import com.harman.meeting_management.mapper.UserMapper;
import com.harman.meeting_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author L.Willian
 * @date 11/7/2019 11:30 AM
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public int addUser(User user) throws DataAccessException {
        return userMapper.insert(user);
    }

    public User findByEmail(String email) {
        return userMapper.selectByEmail(email);
    }

    public User findByEmployeeId(String employeeId) {
        return userMapper.selectByEmployeeId(employeeId);
    }

    public int modifyPre(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public List<Map<String, Object>> findByDepartmentId(Long departmentId) {
        return userMapper.selectByDepartmentId(departmentId);
    }
}
