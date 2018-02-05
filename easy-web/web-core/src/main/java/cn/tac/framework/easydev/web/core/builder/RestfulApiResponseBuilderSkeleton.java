package cn.tac.framework.easydev.web.core.builder;

import cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse;

/**
 * @author tac
 * @since 2.0
 */
public abstract class RestfulApiResponseBuilderSkeleton<T, BUILDER extends RestfulApiResponseBuilderSkeleton<T, BUILDER>>
        implements RestfulApiResponseBuilder<T> {
    private Integer state;
    private String code;
    private String msg;
    private String friendlyMsg;

    public RestfulApiResponseBuilderSkeleton(int state, String code) {
        this.state = state;
        this.code = code;
    }

    public BUILDER msg(String msg) {
        setMsg(msg);
        return (BUILDER) this;
    }

    public BUILDER friendlyMsg(String friendlyMsg) {
        setFriendlyMsg(friendlyMsg);
        return (BUILDER) this;
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
