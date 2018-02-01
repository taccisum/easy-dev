package cn.tac.framework.easydev.dao.core.strategy.id;

import cn.tac.framework.easydev.dao.core.config.DaoCoreProperties;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author tac
 * @since 01/02/2018
 */
public class SnowFlakeIDGeneratorTest {
    @Test
    public void generate() throws Exception {
        DaoCoreProperties.IdGeneratorProperties.SnowFlakeProperties properties = new DaoCoreProperties.IdGeneratorProperties.SnowFlakeProperties();
        properties.setDataCenterId(2);
        properties.setWorkerId(3);
        Set<Long> set = new HashSet<>();
        SnowFlakeIDGenerator.setProperties(properties);
        for (int i = 0; i < (1 << 12); i++) {
            set.add(SnowFlakeIDGenerator.instance().generate());
        }
        assertThat(set.size()).isEqualTo(1 << 12);
    }
}
