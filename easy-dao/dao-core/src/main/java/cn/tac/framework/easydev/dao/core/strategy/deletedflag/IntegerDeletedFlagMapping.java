package cn.tac.framework.easydev.dao.core.strategy.deletedflag;

import cn.tac.framework.easydev.dao.core.strategy.SingletonRegistry;

/**
 * @author tac
 * @since 15/11/2017
 */
public class IntegerDeletedFlagMapping implements DeletedFlagMapping<Integer> {
    private IntegerDeletedFlagMapping(){}

    public static DeletedFlagMapping<Integer> instance(){
        return SingletonRegistry.DELETED_FLAG_MAPPING.register(new IntegerDeletedFlagMapping()).get(IntegerDeletedFlagMapping.class);
    }

    @Override
    public Integer getEnableFlag() {
        return 1;
    }

    @Override
    public Integer getDisableFlag() {
        return 0;
    }
}
