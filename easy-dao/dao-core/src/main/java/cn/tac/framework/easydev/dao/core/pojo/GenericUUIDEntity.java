package cn.tac.framework.easydev.dao.core.pojo;

import cn.tac.framework.easydev.dao.core.strategy.id.IDGenerator;
import cn.tac.framework.easydev.dao.core.strategy.id.UUIDGenerator;

/**
 * 通用的UUID主键的实体基类
 *
 * @author tac
 * @since 15/11/2017
 */
public abstract class GenericUUIDEntity extends GenericEntity<String> {
    @Override
    public IDGenerator<String> getIDGenerator() {
        return UUIDGenerator.instance();
    }
}
