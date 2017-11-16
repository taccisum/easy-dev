package cn.tac.framework.easydev.dao.crud;

import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructure;
import cn.tac.framework.easydev.dao.crud.config.DaoCrudSupportProperties;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 通用的crud repository基类，用于对mapper的操作做一些封装，以简化编码工作
 *
 * @author tac
 * @since 02/11/2017
 */
public abstract class CrudRepositorySupport<E extends MinEntityStructure<PK>, PK>
        implements
        CreationRepositorySupport<E, PK>,
        RetrievalRepositorySupport<E, PK>,
        UpdatingRepositorySupport<E, PK>,
        DeletionRepositorySupport<E, PK> {
    private DaoCrudSupportProperties daoCrudSupportProperties;

    protected CrudMapperSupport<E> mapper;

    /**
     * 由于java泛型的局限性，需要为DAO手动提供实体的类型引用
     */
    protected Class<E> typeReference;
    public CrudRepositorySupport(CrudMapperSupport<E> mapper, Class<E> typeReference) {
        this.mapper = mapper;
        this.typeReference = typeReference;
    }

    @Autowired
    public void setDaoCrudSupportProperties(DaoCrudSupportProperties daoCrudSupportProperties) {
        this.daoCrudSupportProperties = daoCrudSupportProperties;
    }

    @Override
    public DaoCrudSupportProperties getDaoCrudSupportProperties() {
        return daoCrudSupportProperties;
    }

    @Override
    public CrudMapperSupport<E> getMapper() {
        return mapper;
    }

    @Override
    public Class<E> getEntityClass() {
        return typeReference;
    }

    @Override
    public E newEntityInstance() {
        try {
            return typeReference.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
