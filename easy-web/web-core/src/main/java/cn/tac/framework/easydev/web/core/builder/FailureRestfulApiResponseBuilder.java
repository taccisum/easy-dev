package cn.tac.framework.easydev.web.core.builder;

import cn.tac.framework.easydev.core.pojo.AppCode;

import static cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse.FAILURE_STATE;

/**
 * @author tac
 * @since 2.0
 */
public class FailureRestfulApiResponseBuilder<T> extends RestfulApiResponseBuilderSkeleton<T, FailureRestfulApiResponseBuilder<T>> {

    public FailureRestfulApiResponseBuilder() {
        super(FAILURE_STATE, AppCode.DEFAULT_FAILURE_CODE);
        msg(DEFAULT_FAILURE_MSG);
    }

    public FailureRestfulApiResponseBuilder<T> code(String code) {
        setCode(code);
        return this;
    }
}
