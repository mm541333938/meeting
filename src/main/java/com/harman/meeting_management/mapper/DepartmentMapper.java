package com.harman.meeting_management.mapper;

import com.harman.meeting_management.entity.Department;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Department record) throws DataAccessException;

    int insertSelective(Department record) throws DataAccessException;

    Department selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List select();
}