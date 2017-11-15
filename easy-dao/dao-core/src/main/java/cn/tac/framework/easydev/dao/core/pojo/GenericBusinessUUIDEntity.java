package cn.tac.framework.easydev.dao.core.pojo;

import cn.tac.framework.easydev.core.util.IDUtils;

/**
 * 通用的UUID主键的业务实体基类
 *
 * @author tac
 * @since 15/11/2017
 */
public abstract class GenericBusinessUUIDEntity extends GenericBusinessEntity<String> {
    @Override
    protected String newId() {
        return IDUtils.UUID();
    }
}
