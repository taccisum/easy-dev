package cn.tac.framework.easydev.web.client.feign;

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

    @Override
    public Object decode(Response response, Type type) throws IOException, FeignException {
        if (type instanceof Class || type instanceof ParameterizedType || type instanceof WildcardType) {
            HttpMessageConverterExtractor<?> extractor = null;
            if (type instanceof ParameterizedType) {
                ParameterizedType pt = (ParameterizedType) type;
                Type rt = pt.getRawType();
                if (rt instanceof Class) {
                    Class clazz = (Class) rt;
                    if (!RestfulApiResponse.class.isAssignableFrom(clazz)) {
                        ParameterizedTypeImpl wrapperType = ParameterizedTypeImpl.make(RestfulApiResponse.class, new Type[]{type}, null);
                        extractor = new HttpMessageConverterExtractor(wrapperType, this.messageConverters.getObject().getConverters());
                        Object r = extractor.extractData(new DuplicatedFeignResponseAdapter(response));
                        if (r instanceof RestfulApiResponse) {
                            return ((RestfulApiResponse) r).getData();
                        }
                        return r;
                    }
                }
            }
            extractor = new HttpMessageConverterExtractor(type, this.messageConverters.getObject().getConverters());
            return extractor.extractData(new DuplicatedFeignResponseAdapter(response));
        }
        throw new DecodeException("type is not an instance of Class or ParameterizedType: " + type);
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
