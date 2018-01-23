package cn.tac.framework.easydev.core.domain.converter.register.spring;

import cn.tac.framework.easydev.core.domain.converter.Converter;
import cn.tac.framework.easydev.core.domain.converter.ConverterFactory;
import cn.tac.framework.easydev.core.domain.converter.annotation.Register2Factory;
import cn.tac.framework.easydev.core.domain.converter.util.ConverterFactoryUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author tac
 * @since 2.0
 */
public class RegisterConverterProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (ConverterFactoryUtils.isConverterCandidate(bean)) {
            Register2Factory annotation = ConverterFactoryUtils.getRegisterAnnotation(bean);
            ConverterFactory.register((Converter) bean, annotation.from(), annotation.to());
        }
        return bean;
    }
}
