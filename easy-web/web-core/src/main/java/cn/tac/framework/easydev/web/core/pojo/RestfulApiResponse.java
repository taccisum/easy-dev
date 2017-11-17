package cn.tac.framework.easydev.web.core.pojo;

import io.swagger.annotations.ApiModelProperty;

/**
 * restful api统一响应model
 *
 * @author tac
 * @since 1.0
 */
public class RestfulApiResponse<T> {
    public static final String SUCCESS_CODE = "0";
    public static final int SUCCESS_STATE = 1;      //成功
    public static final int FAILURE_STATE = 0;      //失败（业务异常）
    public static final int ERROR_STATE = -1;       //错误（系统异常）

    @ApiModelProperty(value = "请求执行状态", example = "1", allowableValues = "-1, 0, 1")
    private Integer state;
    @ApiModelProperty(value = "请求执行结果状态标识码", example = "0", notes = "请求成功[0-100]，业务异常[101-50000]，系统异常[-1]", allowableValues = "range[0, 50000]")
    private String code;
    @ApiModelProperty(value = "提示信息")
    private String msg;
    @ApiModelProperty(value = "返回数据", example = "{}")
    private T data;
    /**
     * 堆栈追踪信息（请仅在debug模式下返回此值，否则应为空字符串）
     */
    @ApiModelProperty(value = "堆栈追踪信息")
    private String stackTrace;

    public RestfulApiResponse() {
    }

    public RestfulApiResponse(Integer state, String code, String msg) {
        this.state = state;
        this.code = code;
        this.msg = msg;
    }

    public RestfulApiResponse(Integer state, String code, String msg, T data) {
        this.state = state;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public RestfulApiResponse(Integer state, String code, String msg, String stackTrace) {
        this.state = state;
        this.code = code;
        this.msg = msg;
        this.stackTrace = stackTrace;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public static <T> RestfulApiResponse<T> success(String message, T data) {
        return new RestfulApiResponse<>(SUCCESS_STATE, SUCCESS_CODE, message, data);
    }

    public static <T> RestfulApiResponse<T> success(T data) {
        return new RestfulApiResponse<>(SUCCESS_STATE, SUCCESS_CODE, "操作成功", data);
    }

    public static <T> RestfulApiResponse<T> success(String message) {
        return new RestfulApiResponse<>(SUCCESS_STATE, SUCCESS_CODE, message);
    }

    public static <T> RestfulApiResponse<T> success() {
        return new RestfulApiResponse<>(SUCCESS_STATE, SUCCESS_CODE, "操作成功");
    }

    public static <T> RestfulApiResponse<T> failure(String code, String message) {
        return new RestfulApiResponse<>(FAILURE_STATE, code, message, "");
    }

    public static <T> RestfulApiResponse<T> failure(String code, String message, String stackTrace) {
        return new RestfulApiResponse<>(FAILURE_STATE, code, message, stackTrace);
    }

    public static <T> RestfulApiResponse<T> error(String message, String stackTrace) {
        return new RestfulApiResponse<>(ERROR_STATE, "-1", message, stackTrace);
    }
}
