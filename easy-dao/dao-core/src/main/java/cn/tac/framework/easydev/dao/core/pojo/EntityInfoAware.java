package cn.tac.framework.easydev.dao.core.pojo;

import java.util.Date;

/**
 * 与实体本身相关的信息记录
 *
 * @author tac
 * @since 2.0
 */
public interface EntityInfoAware {
    String getCreatedBy();
    void setCreatedBy(String createdBy);
    Date getCreatedOn();
    void setCreatedOn(Date createdOn);
    String getUpdatedBy();
    void setUpdatedBy(String updatedBy);
    Date getUpdatedOn();
    void setUpdatedOn(Date updatedOn);
}
