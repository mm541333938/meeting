package com.harman.meeting_management.service.Impl;

import com.harman.meeting_management.entity.Admin;
import com.harman.meeting_management.mapper.AdminMapper;
import com.harman.meeting_management.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author L.Willian
 * @date 11/8/2019 2:45 PM
 */
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin findByName(String adminName) {
        return adminMapper.selectByName(adminName);
    }
}
