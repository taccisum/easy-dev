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

    public RestfulApiResponseBuilderSkeleton(int state, String code, String msg) {
        this.state = state;
        this.code = code;
        this.msg = msg;
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

    @Override
    public RestfulApiResponse<T> build() {
        RestfulApiResponse<T> response = new RestfulApiResponse<>();
        response.setState(getState());
        response.setCode(getCode());
        response.setMsg(getMsg());
        return response;
    }
}
