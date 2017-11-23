package cn.tac.framework.easydev.dao.middle;

import cn.tac.framework.easydev.dao.core.CrudMapperSupport;
import cn.tac.framework.easydev.dao.core.RepositorySkeleton;
import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.dao.middle.pojo.RelevanceInfoAware;

/**
 * 为中间表repo操作提供支持
 *
 * @author tac
 * @since 2.0
 */
public abstract class MiddleRepositorySupport<E extends MinEntityStructureAware<PK> & RelevanceInfoAware<LPK, RPK>, PK, LPK, RPK> extends RepositorySkeleton<E, PK>
        implements
        CreateRelationRepositorySupport<E, PK, LPK, RPK>,
        DeleteRelationRepositorySupport<E, PK, LPK, RPK>,
        RetrieveRelationRepositorySupport<E, PK, LPK, RPK>,
        UpdateRelationRepositorySupport<E, PK, LPK, RPK> {
    public MiddleRepositorySupport(CrudMapperSupport<E> mapper, Class<E> typeReference) {
        super(mapper, typeReference);
    }

    public int relink(LPK sourceId, RPK... targetIds) {
        int rowNum = 0;
        rowNum += unlinkAll(sourceId);
        rowNum += link(sourceId, targetIds);
        return rowNum;
    }
}
