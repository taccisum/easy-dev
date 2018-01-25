package cn.tac.framework.easydev.test.common;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author tac
 * @since 2.0
 */
public class TestSkeletonTest extends TestSkeleton {
    @Test
    public void testLogger() {
        assertThat(logger).isNotNull();
        if(logger.isTraceEnabled()){
            logger.trace("trace log");
            assertThat(capture.toString()).contains("trace log");
        }
        if(logger.isDebugEnabled()){
            logger.debug("debug log");
            assertThat(capture.toString()).contains("debug log");
        }
        if(logger.isInfoEnabled()){
            logger.info("info log");
            assertThat(capture.toString()).contains("info log");
        }
        if(logger.isWarnEnabled()){
            logger.warn("warn log");
            assertThat(capture.toString()).contains("warn log");
        }
        if(logger.isErrorEnabled()){
            logger.error("error log");
            assertThat(capture.toString()).contains("error log");
        }
    }

    @Test
    public void testFairy() {
        assertThat(fairy).isNotNull();
        String fullName = fairy.person().getFullName();
        assertThat(fullName).isNotBlank();
        System.out.println("hello " + fullName);
    }

    @Test
    public void testCapture() {
        assertThat(capture).isNotNull();
        System.out.println("hello");
        String s = capture.toString();
        assertThat(s).contains("hello");
    }

    @Test
    public void testThrown() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("test");
        throw new IllegalArgumentException("test");
    }
}
