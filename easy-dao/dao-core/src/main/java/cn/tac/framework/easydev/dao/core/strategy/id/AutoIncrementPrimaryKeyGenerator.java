package cn.tac.framework.easydev.dao.core.strategy.id;

import cn.tac.framework.easydev.dao.core.strategy.SingletonRegistry;

/**
 * 自增主键生成器
 *
 * @author tac
 * @since 2.0
 */
public class AutoIncrementPrimaryKeyGenerator implements IDGenerator<Integer> {
    private AutoIncrementPrimaryKeyGenerator() {
    }

    public static IDGenerator<Integer> instance() {
        return SingletonRegistry.ID_GENERATOR.register(new AutoIncrementPrimaryKeyGenerator()).get(AutoIncrementPrimaryKeyGenerator.class);
    }

    @Override
    public Integer generate(Object args) {
        //传入null以让数据库自动生成主键
        return null;
    }
}
