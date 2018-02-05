package cn.tac.framework.easydev.web.core.builder;

import cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse;

/**
 * @author tac
 * @since 2.0
 */
public interface RestfulApiResponseBuilder<T> {
    String DEFAULT_SUCCESS_MSG = "操作成功";
    String DEFAULT_FAILURE_MSG = "未知业务异常";
    String DEFAULT_FAILURE_FRIENDLY_MSG = "无法执行此操作哦";
    String DEFAULT_ERROR_MSG = "系统异常";

    static SuccessRestfulApiResponseBuilder success() {
        return new SuccessRestfulApiResponseBuilder();
    }

    static <T> SuccessRestfulApiResponseBuilder<T> success(Class<T> clazz) {
        return new SuccessRestfulApiResponseBuilder<>();
    }

    static FailureRestfulApiResponseBuilder<Object> failure() {
        return new FailureRestfulApiResponseBuilder<>();
    }

    static <T> FailureRestfulApiResponseBuilder<T> failure(Class<T> clazz) {
        return new FailureRestfulApiResponseBuilder<>();
    }

    static ErrorRestfulApiResponseBuilder<Object> error() {
        return new ErrorRestfulApiResponseBuilder<>();
    }

    static GenericRestfulApiResponseBuilder<Object> generic(int state, String code) {
        return new GenericRestfulApiResponseBuilder<>(state, code);
    }

    static <T> GenericRestfulApiResponseBuilder<T> generic(Class<T> clazz, int state, String code) {
        return new GenericRestfulApiResponseBuilder<>(state, code);
    }

    RestfulApiResponse<T> build();
}
