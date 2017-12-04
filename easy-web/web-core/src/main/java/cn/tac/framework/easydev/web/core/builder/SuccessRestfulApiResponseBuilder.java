package cn.tac.framework.easydev.web.core.builder;

import cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse;

import static cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse.SUCCESS_CODE;
import static cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse.SUCCESS_STATE;

/**
 * @author tac
 * @since 04/12/2017
 */
public class SuccessRestfulApiResponseBuilder<T> extends RestfulApiResponseBuilderSkeleton<T> {
    private T data;

    T getData() {
        return data;
    }

    void setData(T data) {
        this.data = data;
    }

    public SuccessRestfulApiResponseBuilder() {
        super(SUCCESS_STATE, SUCCESS_CODE, "操作成功");
        setData(null);
    }

    public SuccessRestfulApiResponseBuilder<T> msg(String msg) {
        setMsg(msg);
        return this;
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
