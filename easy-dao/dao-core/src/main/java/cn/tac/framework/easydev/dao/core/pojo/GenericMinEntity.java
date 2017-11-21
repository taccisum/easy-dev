package cn.tac.framework.easydev.dao.core.pojo;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @author tac
 * @since 2.0
 */
public abstract class GenericMinEntity<PK> implements MinEntityStructureAware<PK> {
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
