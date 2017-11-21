package cn.tac.framework.easydev.dao.crud;

import cn.tac.framework.easydev.dao.core.api.CrudMapperAware;
import cn.tac.framework.easydev.dao.core.api.EntityClassAware;
import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.dao.core.util.EntityUtils;
import cn.tac.framework.easydev.dao.crud.api.DaoCrudSupportPropertiesAware;

/**
 * todo::
 * 通用的更新方法
 *
 * @author tac
 * @since 2.0
 */
public interface UpdatingRepositorySupport<E extends MinEntityStructureAware<PK>, PK>
        extends CrudMapperAware<E>, EntityClassAware<E>, DaoCrudSupportPropertiesAware {
    default int updateByPrimaryKeySelective(E entity) {
        return updateByPrimaryKeySelective(entity, null, null);
    }

    /**
     * todo:: 暂不支持更新隔离数据
     */
    default int updateByPrimaryKeySelective(E entity, Boolean boundary, Boolean containDeleted) {
        if (entity.getId() == null) {
            return 0;
        }

        boolean _boundary = boundary == null ? getDaoCrudSupportProperties().isBoundary() : boundary;
        boolean _containDeleted = containDeleted == null ? getDaoCrudSupportProperties().isContainDeleted() : containDeleted;
        EntityUtils.initUpdatingInfo(entity);
        if (_boundary) {
            //todo:: 排除隔离的数据
        }
        if (_containDeleted) {
            //todo:: 排队逻辑删除状态的数据
        }
        return getMapper().updateByPrimaryKeySelective(entity);
    }
}
