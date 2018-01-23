package cn.tac.framework.easydev.web.core.builder;

import cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse;

import static cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse.ERROR_CODE;
import static cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse.ERROR_STATE;

/**
 * @author tac
 * @since 2.0
 */
public class ErrorRestfulApiResponseBuilder<T> extends RestfulApiResponseBuilderSkeleton<T> {
    private String stackTrack;

    String getStackTrack() {
        return stackTrack;
    }

    void setStackTrack(String stackTrack) {
        this.stackTrack = stackTrack;
    }

    public ErrorRestfulApiResponseBuilder() {
        super(ERROR_STATE, ERROR_CODE, "系统异常");
    }


    public ErrorRestfulApiResponseBuilder<T> msg(String msg) {
        setMsg(msg);
        return this;
    }

    public ErrorRestfulApiResponseBuilder<T> stackTrack(String stackTrack) {
        setStackTrack(stackTrack);
        return this;
    }

    @Override
    public RestfulApiResponse<T> build() {
        RestfulApiResponse<T> response = super.build();
        response.setStackTrace(stackTrack);
        return response;
    }
}