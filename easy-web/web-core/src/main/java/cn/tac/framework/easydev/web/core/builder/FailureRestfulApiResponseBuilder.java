package cn.tac.framework.easydev.web.core.builder;

import cn.tac.framework.easydev.core.pojo.AppCode;
import cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse;

import static cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse.FAILURE_STATE;

/**
 * @author tac
 * @since 2.0
 */
public class FailureRestfulApiResponseBuilder<T> extends RestfulApiResponseBuilderSkeleton<T, FailureRestfulApiResponseBuilder<T>> {
    private String stackTrace;

    public FailureRestfulApiResponseBuilder() {
        super(FAILURE_STATE, AppCode.DEFAULT_FAILURE_CODE);
        msg(DEFAULT_FAILURE_MSG);
        friendlyMsg(DEFAULT_FAILURE_FRIENDLY_MSG);
    }

    public FailureRestfulApiResponseBuilder<T> code(String code) {
        setCode(code);
        return this;
    }

    public FailureRestfulApiResponseBuilder<T> stackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
        return this;
    }

    @Override
    public RestfulApiResponse<T> build() {
        RestfulApiResponse<T> resp = super.build();
        resp.setStackTrace(stackTrace);
        return resp;
    }
}
