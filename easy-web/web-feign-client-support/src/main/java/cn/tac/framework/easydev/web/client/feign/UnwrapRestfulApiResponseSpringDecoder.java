package cn.tac.framework.easydev.web.client.feign;

import cn.tac.framework.easydev.web.core.exception.RethrowingException;
import cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse;
import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.netflix.feign.support.SpringDecoder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.HttpMessageConverterExtractor;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * @author tac
 * @since 2.3
 */
public class UnwrapRestfulApiResponseSpringDecoder extends SpringDecoder {
    private ObjectFactory<HttpMessageConverters> messageConverters;

    public UnwrapRestfulApiResponseSpringDecoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        super(messageConverters);
        this.messageConverters = messageConverters;
    }

    public Object decode(Response response, Type type) throws IOException, FeignException {
        if (!(type instanceof Class) && !(type instanceof ParameterizedType) && !(type instanceof WildcardType)) {
            throw new DecodeException("type is not an instance of Class or ParameterizedType: " + type);
        } else {
            HttpMessageConverterExtractor<?> extractor = null;
            if (!RestfulApiResponse.class.isAssignableFrom(getRawClass(type))) {
                ParameterizedTypeImpl wrapperType = ParameterizedTypeImpl.make(RestfulApiResponse.class, new Type[]{type}, null);
                extractor = new HttpMessageConverterExtractor(wrapperType, this.messageConverters.getObject().getConverters());
                RestfulApiResponse r = (RestfulApiResponse) extractor.extractData(new UnwrapRestfulApiResponseSpringDecoder.DuplicatedFeignResponseAdapter(response));
                this.check(r);
                return r.getData();
            } else {
                extractor = new HttpMessageConverterExtractor(type, this.messageConverters.getObject().getConverters());
                return extractor.extractData(new UnwrapRestfulApiResponseSpringDecoder.DuplicatedFeignResponseAdapter(response));
            }
        }
    }

    Class getRawClass(Type type) {
        Class clazz = null;
        if (type instanceof Class) {
            clazz = (Class) type;
        } else if (type instanceof ParameterizedType) {
            clazz = (Class) ((ParameterizedType) type).getRawType();
        }
        return clazz;
    }

    private void check(RestfulApiResponse restfulApiResponse) {
        if (restfulApiResponse.getState() != RestfulApiResponse.SUCCESS_STATE) {
            throw new RethrowingException(restfulApiResponse);
        }
    }

    /**
     * this class is copied from {@link SpringDecoder.FeignResponseAdapter}
     * why? because it is private...
     */
    private class DuplicatedFeignResponseAdapter implements ClientHttpResponse {

        private final Response response;

        private DuplicatedFeignResponseAdapter(Response response) {
            this.response = response;
        }

        @Override
        public HttpStatus getStatusCode() throws IOException {
            return HttpStatus.valueOf(this.response.status());
        }

        @Override
        public int getRawStatusCode() throws IOException {
            return this.response.status();
        }

        @Override
        public String getStatusText() throws IOException {
            return this.response.reason();
        }

        @Override
        public void close() {
            try {
                this.response.body().close();
            } catch (IOException ex) {
                // Ignore exception on close...
            }
        }

        @Override
        public InputStream getBody() throws IOException {
            return this.response.body().asInputStream();
        }

        @Override
        public HttpHeaders getHeaders() {
            return getHttpHeaders(this.response.headers());
        }
    }

    static HttpHeaders getHttpHeaders(Map<String, Collection<String>> headers) {
        HttpHeaders httpHeaders = new HttpHeaders();
        for (Map.Entry<String, Collection<String>> entry : headers.entrySet()) {
            httpHeaders.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
        return httpHeaders;
    }
}
