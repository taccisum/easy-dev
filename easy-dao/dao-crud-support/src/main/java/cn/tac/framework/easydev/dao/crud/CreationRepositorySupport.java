package cn.tac.framework.easydev.dao.crud;

import cn.tac.framework.easydev.dao.core.api.CrudMapperAware;
import cn.tac.framework.easydev.dao.core.pojo.InitializingEntity;
import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructure;

/**
 * 通用的新增方法
 *
 * @author tac
 * @since 16/11/2017
 */
public interface CreationRepositorySupport<E extends MinEntityStructure<PK>, PK> extends CrudMapperAware<E> {
    default int insert(E entity) {
        setInsertDefault(entity);
        return getMapper().insert(entity);
    }

    default int insertSelective(E entity) {
        setInsertDefault(entity);
        return getMapper().insertSelective(entity);
    }

    static void setInsertDefault(MinEntityStructure entity) {
        if (entity instanceof InitializingEntity) {
            ((InitializingEntity) entity).init();
        }
    }
}
