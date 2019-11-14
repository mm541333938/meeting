package com.harman.meeting_management.service.Impl;

import com.harman.meeting_management.entity.Room;
import com.harman.meeting_management.mapper.RoomMapper;
import com.harman.meeting_management.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author L.Willian
 * @date 11/13/2019 11:51 AM
 */
@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomMapper roomMapper;

    public int addRoom(Room roomDto) throws DataAccessException {
        return roomMapper.insertSelective(roomDto);
    }

    public List<Room> findAll() {
        return roomMapper.select();
    }

    public int modifyRoomInfo(Room roomDto) {
        return roomMapper.updateByPrimaryKeySelective(roomDto);
    }
}
