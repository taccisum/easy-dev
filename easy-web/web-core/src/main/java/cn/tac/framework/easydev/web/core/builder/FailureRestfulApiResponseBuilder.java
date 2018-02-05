package cn.tac.framework.easydev.web.core.builder;

import cn.tac.framework.easydev.core.pojo.AppCode;
import cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse;

import static cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse.FAILURE_STATE;

/**
 * @author tac
 * @since 2.0
 */
public class FailureRestfulApiResponseBuilder<T> extends RestfulApiResponseBuilderSkeleton<T> {
    private T data;

    T getData() {
        return data;
    }

    void setData(T data) {
        this.data = data;
    }

    public FailureRestfulApiResponseBuilder() {
        super(FAILURE_STATE, AppCode.DEFAULT_FAILURE_CODE, "未知业务异常");
    }

    public FailureRestfulApiResponseBuilder<T> msg(String msg) {
        setMsg(msg);
        return this;
    }

    public FailureRestfulApiResponseBuilder<T> code(String code) {
        setCode(code);
        return this;
    }

    public FailureRestfulApiResponseBuilder<T> data(T data) {
        setData(data);
        return this;
    }

    @Override
    public RestfulApiResponse<T> build() {
        RestfulApiResponse<T> response = super.build();
        response.setData(getData());
        return response;
    }
}
