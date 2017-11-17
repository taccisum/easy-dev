package cn.tac.framework.easydev.dao.core;

import cn.tac.framework.easydev.dao.core.api.CrudMapperAware;
import cn.tac.framework.easydev.dao.core.api.EntityClassAware;
import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructure;

/**
 * @author tac
 * @since 2.0
 */
public abstract class RepositorySkeleton<E extends MinEntityStructure<PK>, PK> implements CrudMapperAware<E>, EntityClassAware<E> {
    protected CrudMapperSupport<E> mapper;

    /**
     * 由于java泛型的局限性，需要为DAO手动提供实体的类型引用
     */
    protected Class<E> typeReference;

    public RepositorySkeleton(CrudMapperSupport<E> mapper, Class<E> typeReference) {
        this.mapper = mapper;
        this.typeReference = typeReference;
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
