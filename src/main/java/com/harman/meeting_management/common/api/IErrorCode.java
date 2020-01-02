package com.harman.meeting_management.common.api;

/**
 * 封装api的错误代码
 *
 * @author L.Willian
 * @date 2019/11/25
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}
