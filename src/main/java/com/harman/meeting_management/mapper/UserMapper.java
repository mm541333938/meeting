package com.harman.meeting_management.mapper;

import com.harman.meeting_management.entity.User;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record) throws DataAccessException;

    int insertSelective(User record) throws DataAccessException;

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByEmail(String email);

    User selectByEmployeeId(String employeeId);

}