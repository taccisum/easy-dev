package cn.tac.framework.easydev.dao.core.strategy;

import cn.tac.framework.easydev.dao.core.exception.NoSuchInstanceException;
import cn.tac.framework.easydev.dao.core.strategy.deletedflag.DeletedFlagMapping;
import cn.tac.framework.easydev.dao.core.strategy.id.IDGenerator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tac
 * @since 2.0
 * @deprecated 不建议继续使用
 */
public class SingletonRegistry<T> {
    public static final SingletonRegistry<DeletedFlagMapping> DELETED_FLAG_MAPPING = new SingletonRegistry<>();
    public static final SingletonRegistry<IDGenerator> ID_GENERATOR = new SingletonRegistry<>();

    SingletonRegistry() {
    }

    private Map<Class<? extends T>, T> instances = new HashMap<>();

    public SingletonRegistry<T> register(T instance) {
        if(instances.containsKey(instance.getClass())){
            return this;
        }
        instances.put((Class<? extends T>) instance.getClass(), instance);
        return this;
    }

    public T get(Class<? extends T> clazz) {
        if (instances.containsKey(clazz)) {
            return instances.get(clazz);
        }
        throw new NoSuchInstanceException(this, clazz);
    }
}
