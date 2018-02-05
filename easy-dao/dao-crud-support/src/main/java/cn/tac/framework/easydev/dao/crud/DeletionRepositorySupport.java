package cn.tac.framework.easydev.dao.crud;

import cn.tac.framework.easydev.dao.core.api.CrudMapperAware;
import cn.tac.framework.easydev.dao.core.api.EntityClassAware;
import cn.tac.framework.easydev.dao.core.pojo.DeletedFlagAware;
import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.dao.crud.api.DaoCrudSupportPropertiesAware;

/**
 * 通用的删除方法
 *
 * @author tac
 * @since 2.0
 */
public interface DeletionRepositorySupport<E extends MinEntityStructureAware<PK>, PK>
        extends CrudMapperAware<E>, EntityClassAware<E>, DaoCrudSupportPropertiesAware {
    default int deleteByPrimaryKey(PK id) {
        return deleteByPrimaryKey(id, null);
    }

    default int deleteByPrimaryKey(PK id, Boolean logicDeleted) {
        boolean _logicDeleted = logicDeleted == null ? getDaoCrudSupportProperties().isLogicDeleted() : logicDeleted;
        if (_logicDeleted) {
            E o = newEntityInstance();
            if (o instanceof DeletedFlagAware) {
                o.setId(id);
                ((DeletedFlagAware) o).setDeletedFlag(
                        ((DeletedFlagAware) o).deletedFlagMapping().getDisableFlag()
                );
                return getMapper().updateByPrimaryKeySelective(o);
            } else {
                return getMapper().deleteByPrimaryKey(id);
            }
        } else {
            return getMapper().deleteByPrimaryKey(id);
        }
    }
}
