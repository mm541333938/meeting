package com.harman.meeting_management.dao;

import com.harman.meeting_management.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author L.Willian
 * @date 2020/1/19
 */
@Repository
public interface UserRoleDao {
    /**
     * 得到相应用户的权限
     *
     * @param userId
     * @return
     */
    List<Role> getRoleList(Long userId);

}
