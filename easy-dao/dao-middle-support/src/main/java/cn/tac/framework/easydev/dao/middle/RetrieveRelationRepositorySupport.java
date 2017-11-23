package cn.tac.framework.easydev.dao.middle;

import cn.tac.framework.easydev.dao.core.api.EntityClassAware;
import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.dao.middle.pojo.RelevanceInfoAware;

import java.util.List;

/**
 * @author tac
 * @since 2.0
 */
public interface RetrieveRelationRepositorySupport<E extends MinEntityStructureAware<PK> & RelevanceInfoAware<LPK, RPK>, PK, LPK, RPK>
        extends EntityClassAware<E> {
    List<E> selectAllRelation(LPK sourceId);

    boolean isRelated(LPK sourceId, RPK targetId);
}
