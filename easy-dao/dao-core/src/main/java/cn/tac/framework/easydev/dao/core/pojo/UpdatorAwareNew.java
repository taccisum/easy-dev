package cn.tac.framework.easydev.dao.core.pojo;

/**
 * 泛化Updator类型
 *
 * @author tac
 * @since 2.0
 */
public interface UpdatorAwareNew<T> {
    T getUpdatedBy();

    void setUpdatedBy(T updatedBy);
}
