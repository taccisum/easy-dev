package cn.tac.framework.easydev.dao.core.bean;

import cn.tac.framework.easydev.core.util.IDUtils;

/**
 * @author tac
 * @since 01/11/2017
 */
public class DefaultRuntimeData4Dao implements RuntimeData4Dao {
    @Override
    public String userId() {
        return IDUtils.emptyUUID();
    }
}
