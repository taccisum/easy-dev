package cn.tac.framework.easydev.dao.core.strategy.id;

import cn.tac.framework.easydev.dao.core.strategy.SingletonRegistry;

/**
 * 自增主键生成器（Long型主键）
 *
 * @author tac
 * @since 2.0
 */
public class AutoIncrementPrimaryKeyGenerator4Long implements IDGenerator<Long> {
    private AutoIncrementPrimaryKeyGenerator4Long() {
    }

    public static IDGenerator<Long> instance() {
        return SingletonRegistry.ID_GENERATOR.register(new AutoIncrementPrimaryKeyGenerator4Long()).get(AutoIncrementPrimaryKeyGenerator4Long.class);
    }

    @Override
    public Long generate(Object args) {
        //mysql数据库中，主键传入0会默认生成自增主键
        //todo:: 其它数据库可能要做适当修改
        return null;
    }
}
