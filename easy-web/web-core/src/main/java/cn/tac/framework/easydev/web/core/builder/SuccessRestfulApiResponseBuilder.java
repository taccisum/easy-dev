package cn.tac.framework.easydev.web.core.builder;

import cn.tac.framework.easydev.core.pojo.AppCode;
import cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse;

import static cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse.SUCCESS_STATE;

/**
 * @author tac
 * @since 2.0
 */
public class SuccessRestfulApiResponseBuilder<T> extends RestfulApiResponseBuilderSkeleton<T, SuccessRestfulApiResponseBuilder<T>> {
    private T data;

    T getData() {
        return data;
    }

    void setData(T data) {
        this.data = data;
    }

    public SuccessRestfulApiResponseBuilder() {
        super(SUCCESS_STATE, AppCode.SUCCESS_CODE);
        msg(DEFAULT_SUCCESS_MSG);
        setData(null);
    }

    public SuccessRestfulApiResponseBuilder<T> data(T data) {
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
