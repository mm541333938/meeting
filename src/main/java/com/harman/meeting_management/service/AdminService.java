package com.harman.meeting_management.service;

import com.harman.meeting_management.entity.Admin;

/**
 * @author L.Willian
 * @date 11/8/2019 2:45 PM
 */
public interface AdminService {
    /**
     * 通过用户名来查询数据
     * @param adminName
     * @return
     */
    public Admin findByName(String adminName);
}
