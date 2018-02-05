package cn.tac.framework.easydev.web.core.pojo;

import io.swagger.annotations.ApiModelProperty;

/**
 * restful api统一响应model
 *
 * @author tac
 * @since 1.0
 */
public class RestfulApiResponse<T> {
    public static final int SUCCESS_STATE = 0;      //成功
    public static final int FAILURE_STATE = 1;      //失败（业务异常）
    public static final int ERROR_STATE = -1;       //错误（系统异常）

    @ApiModelProperty(value = "请求执行状态", example = "1", allowableValues = "-1, 0, 1")
    private Integer state;
    @ApiModelProperty(value = "请求执行结果状态标识码", example = "0", notes = "请求成功[0-99]，业务异常[100-50000]，系统异常[-1]", allowableValues = "range[0, 50000]")
    private String code;
    @ApiModelProperty(value = "提示信息")
    private String msg;
    @ApiModelProperty(value = "友好的提示信息")
    private String friendlyMsg;
    @ApiModelProperty(value = "返回数据", example = "{}")
    private T data;
    /**
     * 堆栈追踪信息（请仅在debug模式下返回此值，否则应为空字符串）
     */
    @ApiModelProperty(value = "堆栈追踪信息")
    private String stackTrace;

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

    public String getFriendlyMsg() {
        return friendlyMsg;
    }

    public void setFriendlyMsg(String friendlyMsg) {
        this.friendlyMsg = friendlyMsg;
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
}
