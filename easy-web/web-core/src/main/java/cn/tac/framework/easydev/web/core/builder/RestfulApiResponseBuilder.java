package cn.tac.framework.easydev.web.core.builder;

import cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse;

/**
 * @author tac
 * @since 04/12/2017
 */
public interface RestfulApiResponseBuilder<T> {
    static SuccessRestfulApiResponseBuilder success() {
        return new SuccessRestfulApiResponseBuilder();
    }

    static <T> SuccessRestfulApiResponseBuilder<T> success(Class<T> clazz) {
        return new SuccessRestfulApiResponseBuilder<>();
    }

    static FailureRestfulApiResponseBuilder failure() {
        return new FailureRestfulApiResponseBuilder();
    }

    static <T> FailureRestfulApiResponseBuilder<T> failure(Class<T> clazz) {
        return new FailureRestfulApiResponseBuilder<>();
    }

    static ErrorRestfulApiResponseBuilder error() {
        return new ErrorRestfulApiResponseBuilder();
    }

    RestfulApiResponse<T> build();
}
