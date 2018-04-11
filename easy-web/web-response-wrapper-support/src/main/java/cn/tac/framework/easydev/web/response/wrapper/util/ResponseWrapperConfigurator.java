package cn.tac.framework.easydev.web.response.wrapper.util;

import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tac
 * @since 2.2
 */
public abstract class ResponseWrapperConfigurator {
    public static void insertHandler(RequestMappingHandlerAdapter adapter, HandlerMethodReturnValueHandler handler, Class<? extends HandlerMethodReturnValueHandler> handlerClass) {
        List<HandlerMethodReturnValueHandler> handlers = new ArrayList<>(adapter.getReturnValueHandlers());
        for (HandlerMethodReturnValueHandler item : handlers) {
            int index = handlers.indexOf(item);
            if (handlerClass.isAssignableFrom(item.getClass())) {
                handlers.add(index, handler);
                break;
            }
        }
        adapter.setReturnValueHandlers(handlers);
    }
}
