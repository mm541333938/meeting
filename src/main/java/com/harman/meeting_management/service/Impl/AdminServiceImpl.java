package com.harman.meeting_management.service.Impl;

import com.harman.meeting_management.entity.Admin;
import com.harman.meeting_management.mapper.AdminMapper;
import com.harman.meeting_management.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author L.Willian
 * @date 11/8/2019 2:45 PM
 */
@Service(value = "adminService")
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    public Admin findByName(String userName) {
        return adminMapper.selectByName(userName);
    }
}
