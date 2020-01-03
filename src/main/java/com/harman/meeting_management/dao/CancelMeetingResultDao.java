package com.harman.meeting_management.dao;

import com.harman.meeting_management.dto.CancelMeetingResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 查询当前用户取消的会议信息的dao
 *
 * @author L.Willian
 * @date 2020/1/3
 */
@Repository
public interface CancelMeetingResultDao {
    /**
     * 通过用户id 查询用户已经取消的会议信息
     *
     * @param uId
     * @return
     */
    List<CancelMeetingResult> selectCancelMeetingByUid(Long uId);
}
