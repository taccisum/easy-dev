package cn.tac.framework.easydev.dao.core.pojo;

import java.util.Date;

/**
 * @author tac
 * @since 22/01/2018
 */
public interface UpdationTimeAware {
    Date getUpdatedOn();
    void setUpdatedOn(Date updatedOn);
}
