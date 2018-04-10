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
        //mysql数据库中，主键传入0会默认生成自增主键
        //todo:: 其它数据库可能要做适当修改
        return null;
    }
}
