package cn.tac.framework.easydev.web.core.builder;

import cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse;

/**
 * @author tac
 * @since 2.0
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

    static GenericRestfulApiResponseBuilder generic(int state, String code, String msg) {
        return new GenericRestfulApiResponseBuilder<>(state, code, msg);
    }

    static GenericRestfulApiResponseBuilder generic(int state, String code, String msg, String friendlyMsg) {
        return new GenericRestfulApiResponseBuilder<>(state, code, msg, friendlyMsg);
    }

    static <T> GenericRestfulApiResponseBuilder<T> generic(Class<T> clazz, int state, String code, String msg) {
        return new GenericRestfulApiResponseBuilder<>(state, code, msg);
    }

    static <T> GenericRestfulApiResponseBuilder<T> generic(Class<T> clazz, int state, String code, String msg, String friendlyMsg) {
        return new GenericRestfulApiResponseBuilder<>(state, code, msg, friendlyMsg);
    }

    RestfulApiResponse<T> build();
}
