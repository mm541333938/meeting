package com.harman.meeting_management.service;


import com.harman.meeting_management.entity.Admin;

/**
 * @author L.Willian
 * @date 11/8/2019 2:45 PM
 */
public interface AdminService {

    public Admin findByName(String userName);

}
