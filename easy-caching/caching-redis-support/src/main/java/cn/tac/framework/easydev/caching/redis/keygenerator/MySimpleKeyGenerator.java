//package cn.tac.framework.easydev.caching.redis.keygenerator;
//
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.cache.interceptor.SimpleKeyGenerator;
//
//import java.lang.reflect.Method;
//
///**
// * @author tac
// * @since 2.0
// */
//public class MySimpleKeyGenerator extends SimpleKeyGenerator {
//    @Override
//    public Object generate(Object target, Method method, Object... params) {
//        String[] values = method.getAnnotation(Cacheable.class).value();
//        if (values.length == 0) {
//            throw new IllegalArgumentException("value");
//        }
//        return values[0] + ":" + super.generate(target, method, params);
//    }
//}
