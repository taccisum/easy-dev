package cn.tac.framework.easydev.dao.core.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 定义实体最小结构，所有的实体都应该实现此接口
 *
 * @author tac
 * @since 15/11/2017
 */
public abstract class MinEntityStructure<PK> implements PrimaryKeyAware<PK>, Serializable {
    public static final String ID_FIELD_NAME = "id";

    @Id
    @Column(name = ID_FIELD_NAME)
    private PK id;

    @Override
    public PK getId() {
        return id;
    }

    @Override
    public void setId(PK id) {
        this.id = id;
    }
}
