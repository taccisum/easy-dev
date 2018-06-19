package cn.tac.framework.easydev.web.client.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * feign 请求头转换 拦截器
 *
 * @author tac
 * @since 2.3
 */
public class HeaderForwardingInterceptor implements RequestInterceptor {
    private Logger logger = LoggerFactory.getLogger(HeaderForwardingInterceptor.class);
    private List<Forwarder> forwarders = new ArrayList<>();

    public HeaderForwardingInterceptor(List<Forwarder> forwarders) {
        this(forwarders, null);
    }

    public HeaderForwardingInterceptor(List<Forwarder> forwarders, Logger logger) {
        this.forwarders = forwarders;
        this.logger = logger == null ? LoggerFactory.getLogger(HeaderForwardingInterceptor.class) : logger;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        logger.debug("forwarding request header");
        logger.debug("try get current request context");
        HttpServletRequest request = getCurrentRequest();
        if (request == null) {
            logger.debug("current request context does not exist");
            return;
        }
        if (CollectionUtils.isEmpty(forwarders)) {
            logger.debug("forwarder is null, won't forwarding any header");
            return;
        }
        logger.debug("try forwarding request headers");
        for (Forwarder forwarder : forwarders) {
            forwarder.forward(request, requestTemplate);
        }
        logger.debug("headers: {}", requestTemplate.headers());
    }

    public static HttpServletRequest getCurrentRequest() throws IllegalStateException {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs == null) {
            return null;
        } else {
            return attrs.getRequest();
        }
    }

    public interface Forwarder {
        void forward(HttpServletRequest request, RequestTemplate template);
    }

    public static abstract class AbstractForwarder implements Forwarder {
        private String targetHeaderName;

        public String getTargetHeaderName() {
            return targetHeaderName;
        }

        public AbstractForwarder(String targetHeaderName) {
            Objects.requireNonNull(targetHeaderName, "target header name");
            this.targetHeaderName = targetHeaderName;
        }

        @Override
        public void forward(HttpServletRequest request, RequestTemplate template) {
            String originHeader = getOriginHeader(request);
            if (!StringUtils.isBlank(originHeader)) {
                forward0(template, originHeader);
            }
        }

        protected abstract String getOriginHeader(HttpServletRequest request);

        protected abstract String getTargetHeader(String originHeader);

        protected void forward0(RequestTemplate template, String originHeader) {
            template.header(getTargetHeaderName(), getTargetHeader(originHeader));
        }
    }

    /**
     * 简单地复制请求头
     */
    public static class CopyHeaderForwarder extends AbstractForwarder {
        public CopyHeaderForwarder(String targetHeaderName) {
            super(targetHeaderName);
        }

        @Override
        protected String getOriginHeader(HttpServletRequest request) {
            return request.getHeader(getTargetHeaderName());
        }

        @Override
        protected String getTargetHeader(String originHeader) {
            return originHeader;
        }
    }

    /**
     * 添加一个请求头
     */
    public abstract static class AppendHeaderForwarder extends AbstractForwarder {
        public AppendHeaderForwarder(String targetHeaderName) {
            super(targetHeaderName);
        }

        @Override
        @Deprecated
        protected String getOriginHeader(HttpServletRequest request) {
            return null;
        }
    }
}
