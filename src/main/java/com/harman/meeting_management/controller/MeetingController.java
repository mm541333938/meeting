package com.harman.meeting_management.controller;

import com.harman.meeting_management.common.api.CommonResult;
import com.harman.meeting_management.dto.MeetingResultParam;
import com.harman.meeting_management.entity.Meeting;
import com.harman.meeting_management.service.MeetingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author L.Willian
 * @date 2020/1/9
 */
@Api(tags = "MeetingController", value = "会议相关查询")
@RestController
public class MeetingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MeetingController.class);

    @Autowired
    private MeetingService meetingService;

    @ApiOperation(value = "按照检索条件查询")
    @GetMapping("/searchMeeting")
    public CommonResult<List<MeetingResultParam>> searchMeeting(@RequestBody Meeting meeting) {
        List<MeetingResultParam> resultList = meetingService.searchMeeting(meeting);
        return CommonResult.success(resultList);
    }

    @ApiOperation(value = "查询指定会议的详细信息")
    @GetMapping("/meetingDetails")
    public CommonResult<Meeting> getMeetingDetailsById(@RequestParam("mId") Long mId) {
        Meeting result = meetingService.getMeetingDetailsByMeetingId(mId);
        return CommonResult.success(result);
    }

    @ApiOperation(value = "通过用户id得到，当前用户已经取消的会议")
    @GetMapping("/canceledMeeting")
    public CommonResult<List<MeetingResultParam>> getCanceledMeeting(@RequestParam("uId") Long uId) {
        List<MeetingResultParam> result = meetingService.getCanceledMeeting(uId);
        return CommonResult.success(result);
    }

    @ApiOperation(value = "通过用户id，得到当前用户未来7天的会议相关信息")
    @GetMapping("/meeting7Days")
    public CommonResult<List<MeetingResultParam>> getMeeting7Days(@RequestParam("uId") Long uId) {
        List<MeetingResultParam> result = meetingService.getMeeting7Days(uId);
        return CommonResult.success(result);
    }

    @ApiOperation(value = "通过用户id，得到当前用户的会议信息")
    @GetMapping("/selfMeeting")
    public CommonResult<List<MeetingResultParam>> getMyMeeting(@RequestParam("uId") Long uId) {
        List<MeetingResultParam> result = meetingService.getMyMeeting(uId);
        return CommonResult.success(result);
    }


}
