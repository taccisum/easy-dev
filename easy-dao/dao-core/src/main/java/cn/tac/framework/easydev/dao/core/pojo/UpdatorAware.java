package cn.tac.framework.easydev.dao.core.pojo;

/**
 * @author tac
 * @since 22/01/2018
 */
public interface UpdatorAware {
    String getUpdatedBy();
    void setUpdatedBy(String updatedBy);
}
