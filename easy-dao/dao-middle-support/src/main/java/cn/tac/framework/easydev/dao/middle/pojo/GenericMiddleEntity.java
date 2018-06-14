package cn.tac.framework.easydev.dao.middle.pojo;

import cn.tac.framework.easydev.dao.core.pojo.GenericInitializingEntity;
import cn.tac.framework.easydev.dao.core.util.EntityUtils;

/**
 * 通用的中间表实体
 *
 * @author tac
 * @since 2.0
 */
public abstract class GenericMiddleEntity<PK, LPK, RPK> extends GenericInitializingEntity<PK> implements RelevanceInfoAware<LPK, RPK> {
    private LPK sourceId;
    private RPK targetId;

    @Override
    public LPK getSourceId() {
        return sourceId;
    }

    @Override
    public void setSourceId(LPK sourceId) {
        this.sourceId = sourceId;
    }

    @Override
    public RPK getTargetId() {
        return targetId;
    }

    @Override
    public void setTargetId(RPK targetId) {
        this.targetId = targetId;
    }

    @Override
    public void init() {
        EntityUtils.init(this);
    }
}
