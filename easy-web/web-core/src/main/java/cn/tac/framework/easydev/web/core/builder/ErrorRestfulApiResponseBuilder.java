package cn.tac.framework.easydev.web.core.builder;

import cn.tac.framework.easydev.core.pojo.AppCode;
import cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse;

import static cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse.ERROR_STATE;

/**
 * @author tac
 * @since 2.0
 */
public class ErrorRestfulApiResponseBuilder<T> extends RestfulApiResponseBuilderSkeleton<T, ErrorRestfulApiResponseBuilder<T>> {
    private String stackTrack;

    void setStackTrack(String stackTrack) {
        this.stackTrack = stackTrack;
    }

    public ErrorRestfulApiResponseBuilder() {
        super(ERROR_STATE, AppCode.SYSTEM_EXCEPTION_CODE);
        msg(DEFAULT_ERROR_MSG);
        friendlyMsg(DEFAULT_ERROR_MSG);
    }

    public ErrorRestfulApiResponseBuilder<T> stackTrace(String stackTrack) {
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
