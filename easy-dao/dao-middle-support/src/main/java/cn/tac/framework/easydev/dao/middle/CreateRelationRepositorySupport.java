package cn.tac.framework.easydev.dao.middle;

import cn.tac.framework.easydev.dao.core.api.CrudMapperAware;
import cn.tac.framework.easydev.dao.core.api.EntityClassAware;
import cn.tac.framework.easydev.dao.core.pojo.InitializingEntity;
import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.dao.middle.pojo.RelevanceInfoAware;

/**
 * @author tac
 * @since 2.0
 */
public interface CreateRelationRepositorySupport<E extends MinEntityStructureAware<PK> & RelevanceInfoAware<LPK, RPK>, PK, LPK, RPK>
        extends EntityClassAware<E>, CrudMapperAware<E> {
    /**
     * 关联id
     */
    default int relate(LPK sourceId, RPK... targetIds) {
        int rowNum = 0;
        //todo:: 批量插入数据，性能待优化
        for (RPK targetId : targetIds) {
            E o = newEntityInstance();
            if(o instanceof InitializingEntity){
                ((InitializingEntity) o).init();
            }
            o.setSourceId(sourceId);
            o.setTargetId(targetId);
            rowNum += getMapper().insert(o);
        }
        return rowNum;
    }

    /**
     * 关联id（反向）
     */
    default int relateInversely(RPK targetId, LPK... sourceIds) {
        int rowNum = 0;
        //todo:: 批量插入数据，性能待优化
        for (LPK sourceId : sourceIds) {
            E o = newEntityInstance();
            if(o instanceof InitializingEntity){
                ((InitializingEntity) o).init();
            }
            o.setSourceId(sourceId);
            o.setTargetId(targetId);
            rowNum += getMapper().insert(o);
        }
        return rowNum;
    }
}
