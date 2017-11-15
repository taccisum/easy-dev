package cn.tac.framework.easydev.dao.core.strategy.id;

import cn.tac.framework.easydev.core.util.IDUtils;
import cn.tac.framework.easydev.dao.core.strategy.SingletonRegistry;

/**
 * UUID生成器
 *
 * @author tac
 * @since 15/11/2017
 */
public class UUIDGenerator implements IDGenerator<String> {
    private UUIDGenerator() {
    }

    public static IDGenerator<String> instance() {
        return SingletonRegistry.ID_GENERATOR.register(new UUIDGenerator()).get(UUIDGenerator.class);
    }

    @Override
    public String generate(Object args) {
        return IDUtils.UUID();
    }
}
