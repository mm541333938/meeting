package com.harman.meeting_management.common.api;

/**
 * 通用返回对象
 *
 * @author L.Willian
 * @date 2019/11/25
 */
public class CommonResult<T> {

    private long code;
    private String message;
    private T data;

    protected CommonResult() {
    }

    protected CommonResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     * 自定义msg
     *
     * @param data
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> success(T data, String msg) {
        return new CommonResult<>(ResultCode.SUCCESS.getCode(), msg, data);
    }

    /**
     * 失败返回结果
     *
     * @param iErrorCode
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> failed(IErrorCode iErrorCode) {
        return new CommonResult<T>(iErrorCode.getCode(), iErrorCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     * 自定义msg
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> failed(String msg) {
        return new CommonResult<T>(ResultCode.FAILED.getCode(), msg, null);
    }

    public static <T> CommonResult<T> failed() {
        return failed(ResultCode.FAILED);
    }

    public static <T> CommonResult<T> failed(long code , String msg){
        return new CommonResult<T>(code, msg, null);
    }

    /**
     * 参数验证失败返回结果
     *
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     * 自定义msg
     *
     * @param message 提示信息
     */
    public static <T> CommonResult<T> validateFailed(String message) {
        return new CommonResult<T>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }


    /**
     * 为登录返回结果
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> unauthorized(T data) {
        return new CommonResult<T>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授权返回结果
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> forbidden(T data) {
        return new CommonResult<T>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
