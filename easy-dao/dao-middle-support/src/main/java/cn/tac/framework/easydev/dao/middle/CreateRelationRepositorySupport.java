package cn.tac.framework.easydev.dao.middle;

import cn.tac.framework.easydev.dao.core.api.EntityClassAware;
import cn.tac.framework.easydev.dao.middle.pojo.GenericMiddleEntity;

/**
 * @author tac
 * @since 2.0
 */
public interface CreateRelationRepositorySupport<E extends GenericMiddleEntity<PK, LPK, RPK>, PK, LPK, RPK>
        extends EntityClassAware<E> {
    int relate(LPK sourceId, RPK... targetIds);
}
