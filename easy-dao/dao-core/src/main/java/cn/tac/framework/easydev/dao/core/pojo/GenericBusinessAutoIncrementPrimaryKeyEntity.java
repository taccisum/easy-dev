package cn.tac.framework.easydev.dao.core.pojo;

import cn.tac.framework.easydev.dao.core.strategy.id.AutoIncrementPrimaryKeyGenerator;
import cn.tac.framework.easydev.dao.core.strategy.id.IDGenerator;

/**
 * 通用的自增主键的业务实体基类
 *
 * @author tac
 * @since 15/11/2017
 */
public abstract class GenericBusinessAutoIncrementPrimaryKeyEntity extends GenericBusinessEntity<Integer> {
    @Override
    public IDGenerator<Integer> getIDGenerator() {
        return AutoIncrementPrimaryKeyGenerator.instance();
    }
}
