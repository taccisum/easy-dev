package cn.tac.framework.easydev.dao.core.pojo;

/**
 * 逻辑删除标识
 *
 * @author tac
 * @since 14/11/2017
 */
public interface DeletedFlagAware {
    Integer getDeletedFlag();
    void setDeletedFlag(Integer flag);
}
