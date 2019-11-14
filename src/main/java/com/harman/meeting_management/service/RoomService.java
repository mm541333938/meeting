package com.harman.meeting_management.service;

import com.harman.meeting_management.entity.Room;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @author L.Willian
 * @date 11/11/2019 4:06 PM
 */
public interface RoomService {

    /**
     * 添加会议室
     *
     * @param roomDto
     * @return
     * @throws DataAccessException
     */
    public int addRoom(Room roomDto);

    /**
     * 查看所有房间信息
     *
     * @return
     */
    public List<Room> findAll();

    /**
     * 修改对应的房间信息
     *
     * @param roomDto
     * @return
     */
    public int modifyRoomInfo(Room roomDto);

    /**
     * 删除对应的房间信息
     *
     * @param roomId
     * @return
     */
    public int deleteByRoomId(Long roomId);

}
