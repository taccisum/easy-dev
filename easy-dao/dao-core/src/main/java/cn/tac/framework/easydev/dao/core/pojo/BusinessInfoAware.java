package cn.tac.framework.easydev.dao.core.pojo;

/**
 * @author tac
 * @since 2.3
 */
public interface BusinessInfoAware<T> {
    T getOrganizationId();

    void setOrganizationId(T orgId);
}
