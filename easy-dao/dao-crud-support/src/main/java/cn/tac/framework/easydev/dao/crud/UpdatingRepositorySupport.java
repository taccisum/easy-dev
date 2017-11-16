package cn.tac.framework.easydev.dao.crud;

import cn.tac.framework.easydev.dao.core.api.CrudMapperAware;
import cn.tac.framework.easydev.dao.core.api.EntityClassAware;
import cn.tac.framework.easydev.dao.core.pojo.EntityInfoAware;
import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructure;
import cn.tac.framework.easydev.dao.core.util.EntityUtils;

/**
 * todo
 * 通用的更新方法
 *
 * @author tac
 * @since 16/11/2017
 */
public interface UpdatingRepositorySupport<E extends MinEntityStructure<PK>, PK>
        extends CrudMapperAware<E>, EntityClassAware<E>, DaoCrudSupportPropertiesAware {
    default int updateByPrimaryKeySelective(E entity, Boolean boundary, Boolean containDeleted) {
        if (entity.getId() == null) {
            return 0;
        }

        boolean _boundary = boundary == null ? getDaoCrudSupportProperties().isBoundary() : boundary;
        boolean _containDeleted = containDeleted == null ? getDaoCrudSupportProperties().isContainDeleted() : containDeleted;
        if(entity instanceof EntityInfoAware){
            EntityUtils.initUpdatingInfo((EntityInfoAware) entity);
        }
        if(_boundary){
            //todo:: 排除隔离的数据
        }
        if(_containDeleted){
            //todo:: 排队逻辑删除状态的数据
        }
        return getMapper().updateByPrimaryKeySelective(entity);
    }
}
