package cn.tac.framework.easydev.dao.crud;

import cn.tac.framework.easydev.dao.core.CrudMapperSupport;
import cn.tac.framework.easydev.dao.core.RepositorySkeleton;
import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.dao.crud.config.DaoCrudSupportProperties;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 通用的crud repository基类，用于对mapper的操作做一些封装，以简化编码工作
 *
 * @author tac
 * @since 2.0
 */
public abstract class CrudRepositorySupport<E extends MinEntityStructureAware<PK>, PK> extends RepositorySkeleton<E, PK>
        implements
        CreationRepositorySupport<E, PK>,
        RetrievalRepositorySupport<E, PK>,
        UpdatingRepositorySupport<E, PK>,
        DeletionRepositorySupport<E, PK> {
    private DaoCrudSupportProperties daoCrudSupportProperties;

    public CrudRepositorySupport(CrudMapperSupport<E> mapper, Class<E> typeReference) {
        super(mapper, typeReference);
    }

    @Override
    public DaoCrudSupportProperties getDaoCrudSupportProperties() {
        return daoCrudSupportProperties;
    }

    @Autowired
    public void setDaoCrudSupportProperties(DaoCrudSupportProperties daoCrudSupportProperties) {
        this.daoCrudSupportProperties = daoCrudSupportProperties;
    }
}
