package cn.tac.framework.easydev.caching.core.api;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.cache.CacheManager;

/**
 * @author tac
 * @since 2.0
 */
public abstract class CacheManagerFactoryBean<T extends CacheManager> implements FactoryBean<CacheManager> {
    @Override
    public CacheManager getObject() throws Exception {
        return getCacheManager();
    }

    @Override
    public Class<?> getObjectType() {
        return CacheManager.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    protected abstract T getCacheManager();
}
