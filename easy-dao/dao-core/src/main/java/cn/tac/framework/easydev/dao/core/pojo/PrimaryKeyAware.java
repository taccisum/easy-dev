package cn.tac.framework.easydev.dao.core.pojo;

import cn.tac.framework.easydev.dao.core.strategy.id.IDGenerator;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 主键
 *
 * @author tac
 * @since 2.0
 */
public interface PrimaryKeyAware<PK> {
    PK getId();
    void setId(PK id);

    @JsonIgnore
    IDGenerator<PK> getIDGenerator();
}
