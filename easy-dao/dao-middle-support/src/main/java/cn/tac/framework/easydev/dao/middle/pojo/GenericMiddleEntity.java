package cn.tac.framework.easydev.dao.middle.pojo;

import cn.tac.framework.easydev.dao.core.pojo.InitializingEntity;
import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.dao.core.util.EntityUtils;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * @author tac
 * @since 2.0
 */
public abstract class GenericMiddleEntity<PK, LPK, RPK> extends MinEntityStructureAware<PK> implements InitializingEntity, RelevanceInfoAware<LPK, RPK> {
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

    @Override
    public void init() {
        EntityUtils.init(this);
    }
}
