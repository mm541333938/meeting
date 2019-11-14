package com.harman.meeting_management.mapper;

import com.harman.meeting_management.entity.Room;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RoomMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Room record) throws DataAccessException;

    int insertSelective(Room record) throws DataAccessException;

    List<Room> select();

    List<Map<String,Object>> selectRoomAble();

    int updateByPrimaryKeySelective(Room record);

    int updateByPrimaryKey(Room record);
}