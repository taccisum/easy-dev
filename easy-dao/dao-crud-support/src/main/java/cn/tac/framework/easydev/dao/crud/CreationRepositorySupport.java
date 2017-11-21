package cn.tac.framework.easydev.dao.crud;

import cn.tac.framework.easydev.dao.core.api.CrudMapperAware;
import cn.tac.framework.easydev.dao.core.pojo.InitializingEntity;
import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;

/**
 * 通用的新增方法
 *
 * @author tac
 * @since 2.0
 */
public interface CreationRepositorySupport<E extends MinEntityStructureAware<PK>, PK> extends CrudMapperAware<E> {
    default int insert(E entity) {
        setInsertDefault(entity);
        return getMapper().insert(entity);
    }

    default int insertSelective(E entity) {
        setInsertDefault(entity);
        return getMapper().insertSelective(entity);
    }

    static void setInsertDefault(MinEntityStructureAware entity) {
        if (entity instanceof InitializingEntity) {
            ((InitializingEntity) entity).init();
        }
    }
}
