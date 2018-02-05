package cn.tac.framework.easydev.dao.crud;

import cn.tac.framework.easydev.dao.core.api.CrudMapperAware;
import cn.tac.framework.easydev.dao.core.api.EntityClassAware;
import cn.tac.framework.easydev.dao.core.pojo.DeletedFlagAware;
import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.dao.core.util.EntityUtils;
import cn.tac.framework.easydev.dao.crud.api.DaoCrudSupportPropertiesAware;

import java.util.List;

/**
 * 通用查询方法
 * todo:: select by example
 *
 * @author tac
 * @since 2.0
 */
public interface RetrievalRepositorySupport<E extends MinEntityStructureAware<PK>, PK>
        extends CrudMapperAware<E>, EntityClassAware<E>, DaoCrudSupportPropertiesAware {
    default List<E> select(E criteria) {
        return select(criteria, null, null);
    }

    default List<E> select(E criteria, Boolean boundary, Boolean containDeleted) {
        boolean _boundary = boundary == null ? getDaoCrudSupportProperties().isBoundary() : boundary;
        boolean _containDeleted = containDeleted == null ? getDaoCrudSupportProperties().isContainDeleted() : containDeleted;

        if (_boundary) {
            filterByBoundary(criteria);
        }
        if (!_containDeleted) {
            filterDeleted(criteria);
        }
        return getMapper().select(criteria);
    }

    default List<E> selectAll() {
        return selectAll(null, null);
    }

    default List<E> selectAll(Boolean boundary, Boolean containDeleted) {
        E o = newEntityInstance();
        return select(o, boundary, containDeleted);
    }

    default E selectByPrimaryKey(PK id) {
        return selectByPrimaryKey(id, null, null);
    }

    default E selectByPrimaryKey(PK id, Boolean boundary, Boolean containDeleted) {
        if (null == id) {
            return null;
        }
        E o = newEntityInstance();
        o.setId(id);
        List<E> ls = select(o, boundary, containDeleted);
        return ls.size() > 0 ? ls.get(0) : null;
    }

    static void filterByBoundary(MinEntityStructureAware entity) {
        EntityUtils.setBoundary4Query(entity);
    }

    static void filterDeleted(MinEntityStructureAware entity) {
        if (entity instanceof DeletedFlagAware) {
            DeletedFlagAware _entity = (DeletedFlagAware) entity;
            _entity.setDeletedFlag(_entity.deletedFlagMapping().getEnableFlag());
        }
    }
}
