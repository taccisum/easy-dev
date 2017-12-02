package cn.tac.framework.easydev.dao.middle;

import cn.tac.framework.easydev.dao.core.api.CrudMapperAware;
import cn.tac.framework.easydev.dao.core.api.EntityClassAware;
import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.dao.middle.pojo.RelevanceInfoAware;

import java.util.List;

/**
 * @author tac
 * @since 2.0
 */
public interface RetrieveRelationRepositorySupport<E extends MinEntityStructureAware<PK> & RelevanceInfoAware<LPK, RPK>, PK, LPK, RPK>
        extends EntityClassAware<E>, CrudMapperAware<E> {
    default List<E> selectAllRelation(LPK sourceId) {
        E o = newEntityInstance();
        o.setSourceId(sourceId);
        return getMapper().select(o);
    }

    default List<E> selectAllRelationInversely(RPK targetId) {
        E o = newEntityInstance();
        o.setTargetId(targetId);
        return getMapper().select(o);
    }

    default boolean anyRelation(LPK sourceId, RPK targetId) {
        E o = newEntityInstance();
        o.setSourceId(sourceId);
        o.setTargetId(targetId);
        return getMapper().selectCount(o) > 0;
    }
}
