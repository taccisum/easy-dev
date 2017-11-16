package cn.tac.framework.easydev.dao.middle.pojo;

import cn.tac.framework.easydev.dao.core.pojo.InitializingEntity;
import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructure;
import cn.tac.framework.easydev.dao.core.util.EntityUtils;

import javax.persistence.Column;
import javax.persistence.Id;

import static cn.tac.framework.easydev.dao.core.pojo.GenericEntity.ID_FIELD_NAME;

/**
 * @author tac
 * @since 17/11/2017
 */
public abstract class GenericMiddleEntity<PK, LPK, RPK> implements MinEntityStructure<PK>, InitializingEntity, RelevanceInfoAware<LPK, RPK> {
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
