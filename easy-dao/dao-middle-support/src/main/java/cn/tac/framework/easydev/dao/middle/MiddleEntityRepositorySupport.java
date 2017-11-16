package cn.tac.framework.easydev.dao.middle;

import cn.tac.framework.easydev.dao.core.CrudMapperSupport;
import cn.tac.framework.easydev.dao.core.RepositorySkeleton;
import cn.tac.framework.easydev.dao.middle.pojo.GenericMiddleEntity;

/**
 * @author tac
 * @since 17/11/2017
 */
public abstract class MiddleEntityRepositorySupport<E extends GenericMiddleEntity<PK, LPK, RPK>, PK, LPK, RPK> extends RepositorySkeleton<E, PK>
        implements
        CreateRelationRepositorySupport<E, PK, LPK, RPK>,
        DeleteRelationRepositorySupport<E, PK, LPK, RPK>,
        RetrieveRelationRepositorySupport<E, PK, LPK, RPK>,
        UpdateRelationRepositorySupport<E, PK, LPK, RPK> {
    public MiddleEntityRepositorySupport(CrudMapperSupport<E> mapper, Class<E> typeReference) {
        super(mapper, typeReference);
    }

    public int rerelate(LPK sourceId, RPK... targetIds) {
        int rowNum = 0;
        rowNum += unrelate(sourceId);
        rowNum += relate(sourceId, targetIds);
        return rowNum;
    }
}
