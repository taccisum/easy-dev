package cn.tac.framework.easydev.core.domain.strategy.register.spring;

import cn.tac.framework.easydev.core.domain.strategy.Strategy;
import cn.tac.framework.easydev.core.domain.strategy.StrategyFactory;
import cn.tac.framework.easydev.core.domain.strategy.annotation.RegisterStrategy;
import cn.tac.framework.easydev.core.domain.strategy.exception.TypeErrorStrategyException;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author tac
 * @since 2.3
 */
public class RegisterStrategyProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean != null && bean instanceof Strategy) {
            RegisterStrategy annotation = bean.getClass().getAnnotation(RegisterStrategy.class);
            if (annotation != null && annotation.register()) {
                if (!annotation.type().isAssignableFrom(bean.getClass())) {
                    throw new TypeErrorStrategyException(annotation.type(), bean.getClass());
                }
                StrategyFactory.register(annotation.type(), annotation.key(), (Strategy) bean);
            }
        }
        return bean;
    }
}
