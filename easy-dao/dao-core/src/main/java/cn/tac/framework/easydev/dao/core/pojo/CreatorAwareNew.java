package cn.tac.framework.easydev.dao.core.pojo;

/**
 * 泛化Creator类型
 *
 * @author tac
 * @since 2.3
 */
public interface CreatorAwareNew<T> {
    T getCreatedBy();

    void setCreatedBy(T createdBy);
}
