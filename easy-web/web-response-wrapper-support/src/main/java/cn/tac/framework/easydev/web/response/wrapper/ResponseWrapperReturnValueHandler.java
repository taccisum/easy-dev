package cn.tac.framework.easydev.web.response.wrapper;

import cn.tac.framework.easydev.web.core.builder.RestfulApiResponseBuilder;
import cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse;
import cn.tac.framework.easydev.web.response.wrapper.annotation.GenericResponse;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

/**
 * @author tac
 * @since 2.2
 */
public class ResponseWrapperReturnValueHandler implements HandlerMethodReturnValueHandler {
    private HandlerMethodReturnValueHandler delegate;

    public ResponseWrapperReturnValueHandler(RequestResponseBodyMethodProcessor delegate) {
        this.delegate = delegate;
    }

    /**
     * 只对{@link RestfulApiResponse}的返回类型进行适配
     */
    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        return delegate.supportsReturnType(methodParameter) && methodParameter.hasMethodAnnotation(GenericResponse.class);
    }

    @Override
    public void handleReturnValue(Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception {
        Object result;
        if (o instanceof RestfulApiResponse) {
            result = o;
        } else {
            GenericResponse genericResponse = methodParameter.getMethodAnnotation(GenericResponse.class);
            result = RestfulApiResponseBuilder.success(Object.class)
                    .data(o)
                    .msg(RestfulApiResponseBuilder.DEFAULT_SUCCESS_MSG)
                    .friendlyMsg(genericResponse.msg())
                    .build();
        }
        delegate.handleReturnValue(result, methodParameter, modelAndViewContainer, nativeWebRequest);
    }
}
