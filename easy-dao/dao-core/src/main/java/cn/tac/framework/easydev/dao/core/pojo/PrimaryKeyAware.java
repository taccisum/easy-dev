package cn.tac.framework.easydev.dao.core.pojo;

/**
 * 主键
 *
 * @author tac
 * @since 15/11/2017
 */
public interface PrimaryKeyAware<PK> {
    PK getId();
    void setId(PK id);
}
