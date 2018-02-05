package cn.tac.framework.easydev.dao.core.pojo;

/**
 * @author tac
 * @since 22/01/2018
 */
public interface CreatorAware<UPK> {
    UPK getCreatedBy();
    void setCreatedBy(UPK createdBy);
}
