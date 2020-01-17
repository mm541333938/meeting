package com.harman.meeting_management.service.Impl;

import com.harman.meeting_management.common.util.JwtTokenUtil;
import com.harman.meeting_management.dto.UserDetailsDto;
import com.harman.meeting_management.entity.User;
import com.harman.meeting_management.entity.UserT;
import com.harman.meeting_management.mapper.UserMapper;
import com.harman.meeting_management.mapper.UserTMapper;
import com.harman.meeting_management.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author L.Willian
 * @date 11/7/2019 11:30 AM
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserTMapper userTMapper;

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

    @Override
    public UserDetailsDto findByName(String userName) {
        return userTMapper.selectByUsername(userName);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserT register(UserT userParam) {
        userParam.setCreateTime(new Date());
        //userParam.setStatus(1);
        //查询是否有相同用户名的用户
        UserDetailsDto username = userTMapper.selectByUsername(userParam.getUsername());
        if (username != null) return null;
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(userParam.getPassword());
        userParam.setPassword(encodePassword);
        userTMapper.insertSelective(userParam);
        return userParam;
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
            //insertLoginLog(username); //增加登录记录
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    /**
     * 增加登录记录
     *
     * @param username
     */
    private void insertLoginLog(String username) {
        /*userTMapper.selectByUsername(username);
        loginLogMapper.insert(loginLog);*/
    }


    public String refreshToken(String oldToken) {
        return jwtTokenUtil.refreshToken(oldToken);
    }
}
