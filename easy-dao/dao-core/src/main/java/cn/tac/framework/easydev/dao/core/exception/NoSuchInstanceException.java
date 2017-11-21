package cn.tac.framework.easydev.dao.core.exception;

import cn.tac.framework.easydev.core.exception.EasyDevException;
import cn.tac.framework.easydev.dao.core.strategy.SingletonRegistry;

/**
 * @author tac
 * @since 2.0
 */
public class NoSuchInstanceException extends EasyDevException {
    public <T> NoSuchInstanceException(SingletonRegistry<T> registry, Class<? extends T> clazz) {
        super("instance of class " + clazz + " does not exist in " + registry.getClass());
    }
}
