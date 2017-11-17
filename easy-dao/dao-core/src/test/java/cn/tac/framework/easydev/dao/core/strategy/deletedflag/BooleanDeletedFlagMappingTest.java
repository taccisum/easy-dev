package cn.tac.framework.easydev.dao.core.strategy.deletedflag;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author tac
 * @since 2.0
 */
public class BooleanDeletedFlagMappingTest {
    @Test
    public void testSimply() {
        DeletedFlagMapping<Boolean> instance = BooleanDeletedFlagMapping.instance();
        assertThat(instance).isNotNull();
        assertThat(instance.getEnableFlag()).isEqualTo(true);
        assertThat(instance.getDisableFlag()).isEqualTo(false);
    }
}
