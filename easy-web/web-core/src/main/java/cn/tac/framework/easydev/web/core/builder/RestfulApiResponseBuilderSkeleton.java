package cn.tac.framework.easydev.web.core.builder;

import cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse;

/**
 * @author tac
 * @since 2.0
 */
public abstract class RestfulApiResponseBuilderSkeleton<T> implements RestfulApiResponseBuilder<T> {
    private Integer state;
    private String code;
    private String msg;
    private String friendlyMsg;

    public RestfulApiResponseBuilderSkeleton(int state, String code, String msg) {
        this(state, code, msg, null);
    }

    public RestfulApiResponseBuilderSkeleton(int state, String code, String msg, String friendlyMsg) {
        this.state = state;
        this.code = code;
        this.msg = msg;
        this.friendlyMsg = friendlyMsg;
    }

    Integer getState() {
        return state;
    }

    void setState(Integer state) {
        this.state = state;
    }

    String getCode() {
        return code;
    }

    void setCode(String code) {
        this.code = code;
    }

    String getMsg() {
        return msg;
    }

    void setMsg(String msg) {
        this.msg = msg;
    }

    String getFriendlyMsg() {
        return friendlyMsg;
    }

    void setFriendlyMsg(String friendlyMsg) {
        this.friendlyMsg = friendlyMsg;
    }

    @Override
    public RestfulApiResponse<T> build() {
        RestfulApiResponse<T> response = new RestfulApiResponse<>();
        response.setState(getState());
        response.setCode(getCode());
        response.setMsg(getMsg());
        response.setFriendlyMsg(friendlyMsg);
        return response;
    }
}
