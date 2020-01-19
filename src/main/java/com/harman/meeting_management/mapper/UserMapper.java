package com.harman.meeting_management.mapper;

import com.harman.meeting_management.dto.UserDetailsDto;
import com.harman.meeting_management.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<UserDetailsDto> selectByUsername(String userName);

    User findByUsername(String userName);

    List<Map<String, Object>> selectByDepartmentId(Long departmentId);

    User selectByEmail(String email);

    User selectByEmployeeId(String employeeId);

    UserDetailsDto selectByUserDetails(String s);
}