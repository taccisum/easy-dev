package cn.tac.framework.easydev.dao.core.strategy.id;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author tac
 * @since 2.0
 */
public class UUIDGeneratorTest {
    @Test
    public void generate() throws Exception {
        IDGenerator<String> instance = UUIDGenerator.instance();
        assertThat(instance).isNotNull();
        assertThat(instance.generate()).isNotBlank();
    }
}
