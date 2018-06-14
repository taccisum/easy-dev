package cn.tac.framework.easydev.dao.core.pojo;

/**
 * @author tac
 * @since 2.3
 */
public interface TenantAware<T> {
    T getTenantId();

    void setTenantId(T tenantId);
}
