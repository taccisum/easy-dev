package cn.tac.framework.easydev.service.crud;

import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.service.core.api.RepositoryAware;
import cn.tac.framework.easydev.service.crud.pojo.QueryCriteria;
import com.github.pagehelper.PageInfo;

/**
 * @author tac
 * @since 17/11/2017
 */
public interface RetrievalServiceSupport<E extends MinEntityStructureAware<PK>, PK> extends RepositoryAware<E, PK> {
    E get(PK pk);

    PageInfo<E> list(E criteria, int index, int size);

    PageInfo<E> list(QueryCriteria criteria);
}
