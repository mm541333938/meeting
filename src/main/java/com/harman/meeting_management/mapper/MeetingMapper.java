package com.harman.meeting_management.mapper;

import com.harman.meeting_management.entity.Meeting;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeetingMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Meeting record);

    int insertSelective(Meeting record) throws DataAccessException;

    Meeting selectByPrimaryKey(Long id);

    List<Meeting> selectSelective(Meeting meetingDto);

    int updateByPrimaryKeySelective(Meeting record);

    int updateByPrimaryKey(Meeting record);
}