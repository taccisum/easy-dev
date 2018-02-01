package cn.tac.framework.easydev.dao.core.strategy.id;

import cn.tac.framework.easydev.core.util.IDUtils;
import cn.tac.framework.easydev.dao.core.config.DaoCoreProperties;
import cn.tac.framework.easydev.dao.core.strategy.SingletonRegistry;

import java.util.Objects;

/**
 * 采用Twitter snowflake算法的id生成器
 *
 * @author tac
 * @since 2.0
 */
public class SnowFlakeIDGenerator implements IDGenerator<Long> {
    private static IDGenerator<Long> instance;
    private static DaoCoreProperties.IdGeneratorProperties.SnowFlakeProperties snowFlakeProperties;
    static IDUtils.SnowFlake snowflake;

    private SnowFlakeIDGenerator(long datacenterId, long machineId) {
        snowflake = IDUtils.snowflake(datacenterId, machineId);
    }

    public static IDGenerator<Long> instance() {
        if (instance == null) {
            Objects.requireNonNull(snowFlakeProperties, "you must config snowflake properties before using SnowFlakeIDGenerator");
            instance = SingletonRegistry.ID_GENERATOR.register(new SnowFlakeIDGenerator(snowFlakeProperties.getDataCenterId(), snowFlakeProperties.getWorkerId())).get(SnowFlakeIDGenerator.class);
        }
        return instance;
    }

    public static void setProperties(DaoCoreProperties daoCoreProperties) {
        setProperties(daoCoreProperties.getIdGenerator().getSnowFlake());
    }

    public static void setProperties(DaoCoreProperties.IdGeneratorProperties.SnowFlakeProperties snowFlakeProperties) {
        SnowFlakeIDGenerator.snowFlakeProperties = snowFlakeProperties;
    }

    @Override
    public Long generate(Object args) {
        return snowflake.nextId();
    }
}
