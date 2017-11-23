package cn.tac.framework.easydev.dao.middle;

import cn.tac.framework.easydev.dao.core.api.CrudMapperAware;
import cn.tac.framework.easydev.dao.core.api.EntityClassAware;
import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.dao.middle.pojo.RelevanceInfoAware;
import tk.mybatis.mapper.entity.Example;

/**
 * @author tac
 * @since 2.0
 */
public interface DeleteRelationRepositorySupport<E extends MinEntityStructureAware<PK> & RelevanceInfoAware<LPK, RPK>, PK, LPK, RPK>
        extends EntityClassAware<E>, CrudMapperAware<E> {
    default int unlink(LPK sourceId, RPK... targetIds) {
        if (targetIds.length == 0) {
            return 0;
        }

        Example example = new Example(getEntityClass());
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("sourceId", sourceId);

        Example.Criteria criteria1 = example.createCriteria();
        for (RPK targetId : targetIds) {
            criteria1.orEqualTo("targetId", targetId);
        }
        example.and(criteria1);
        return getMapper().deleteByExample(example);
    }

    default int unlinkInversely(RPK targetId, LPK... sourceIds) {
        if (sourceIds.length == 0) {
            return 0;
        }

        Example example = new Example(getEntityClass());
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("targetId", targetId);

        Example.Criteria criteria1 = example.createCriteria();
        for (LPK sourceId : sourceIds) {
            criteria1.orEqualTo("sourceId", sourceId);
        }
        example.and(criteria1);
        return getMapper().deleteByExample(example);
    }

    default int unlinkAll(LPK sourceId) {
        E o = newEntityInstance();
        o.setSourceId(sourceId);
        return getMapper().delete(o);
    }

    default int unlinkAllInversely(RPK targetId) {
        E o = newEntityInstance();
        o.setTargetId(targetId);
        return getMapper().delete(o);
    }
}
