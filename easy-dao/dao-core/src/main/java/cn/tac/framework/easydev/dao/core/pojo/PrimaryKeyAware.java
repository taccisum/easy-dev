package cn.tac.framework.easydev.dao.core.pojo;

import cn.tac.framework.easydev.dao.core.strategy.id.IDGenerator;

/**
 * 主键
 *
 * @author tac
 * @since 15/11/2017
 */
public interface PrimaryKeyAware<PK> {
    PK getId();
    void setId(PK id);

    IDGenerator<PK> getIDGenerator();
}
