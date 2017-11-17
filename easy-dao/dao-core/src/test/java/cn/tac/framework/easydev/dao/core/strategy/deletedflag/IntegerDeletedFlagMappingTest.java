package cn.tac.framework.easydev.dao.core.strategy.deletedflag;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author tac
 * @since 2.0
 */
public class IntegerDeletedFlagMappingTest {
    @Test
    public void testSimply() throws Exception {
        DeletedFlagMapping<Integer> instance = IntegerDeletedFlagMapping.instance();
        assertThat(instance).isNotNull();
        assertThat(instance.getEnableFlag()).isEqualTo(IntegerDeletedFlagMapping.ENABLE_FLAG);
        assertThat(instance.getDisableFlag()).isEqualTo(IntegerDeletedFlagMapping.DISABLE_FLAG);
    }
}
