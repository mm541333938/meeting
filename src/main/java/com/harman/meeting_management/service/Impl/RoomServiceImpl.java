package com.harman.meeting_management.service.Impl;

import com.harman.meeting_management.entity.Room;
import com.harman.meeting_management.mapper.RoomMapper;
import com.harman.meeting_management.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

/**
 * @author L.Willian
 * @date 11/13/2019 11:51 AM
 */
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomMapper roomMapper;

    public int addRoom(Room roomDto) throws DataAccessException {
        return roomMapper.insertSelective(roomDto);
    }
}
