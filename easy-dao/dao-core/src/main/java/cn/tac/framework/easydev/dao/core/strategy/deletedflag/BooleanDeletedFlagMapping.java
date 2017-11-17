package cn.tac.framework.easydev.dao.core.strategy.deletedflag;

import cn.tac.framework.easydev.dao.core.strategy.SingletonRegistry;

/**
 * @author tac
 * @since 2.0
 */
public class BooleanDeletedFlagMapping implements DeletedFlagMapping<Boolean> {
    private BooleanDeletedFlagMapping(){}

    public static DeletedFlagMapping<Boolean> instance(){
        return SingletonRegistry.DELETED_FLAG_MAPPING.register(new BooleanDeletedFlagMapping()).get(BooleanDeletedFlagMapping.class);
    }

    @Override
    public Boolean getEnableFlag() {
        return true;
    }

    @Override
    public Boolean getDisableFlag() {
        return false;
    }
}
