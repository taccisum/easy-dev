package cn.tac.framework.easydev.dao.core.pojo;

/**
 * @author tac
 * @since 22/01/2018
 */
public interface UpdatorAware<UPK> {
    UPK getUpdatedBy();
    void setUpdatedBy(UPK updatedBy);
}
