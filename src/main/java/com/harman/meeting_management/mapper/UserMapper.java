package com.harman.meeting_management.mapper;

import com.harman.meeting_management.entity.User;
import com.harman.meeting_management.entity.UserT;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record) throws DataAccessException;

    int insertSelective(User record) throws DataAccessException;

    List<Map<String, Object>> selectByDepartmentId(Long departmentId);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByEmail(String email);

    User selectByEmployeeId(String employeeId);


}