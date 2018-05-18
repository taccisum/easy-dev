package cn.tac.framework.easydev.logging.support.logstash;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试写日志到redis-logstash请先确保redis连通
 *
 * @author tac
 * @since 2.3
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleTest.class)
@SpringBootApplication
//@Ignore
public class SampleTest {
    private Logger logger = LoggerFactory.getLogger(SampleTest.class);

    @Test
    public void testSimply() throws Exception {
        logger.info("test simply");
    }
}
