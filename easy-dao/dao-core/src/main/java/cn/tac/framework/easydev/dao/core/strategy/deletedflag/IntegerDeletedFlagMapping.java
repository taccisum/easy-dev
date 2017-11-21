package cn.tac.framework.easydev.dao.core.strategy.deletedflag;

import cn.tac.framework.easydev.dao.core.strategy.SingletonRegistry;

/**
 * @author tac
 * @since 2.0
 */
public class IntegerDeletedFlagMapping implements DeletedFlagMapping<Integer> {
    public static final int ENABLE_FLAG = 0;
    public static final int DISABLE_FLAG = 1;

    private IntegerDeletedFlagMapping(){}

    public static DeletedFlagMapping<Integer> instance(){
        return SingletonRegistry.DELETED_FLAG_MAPPING.register(new IntegerDeletedFlagMapping()).get(IntegerDeletedFlagMapping.class);
    }

    @Override
    public Integer getEnableFlag() {
        return ENABLE_FLAG;
    }

    @Override
    public Integer getDisableFlag() {
        return DISABLE_FLAG;
    }
}
