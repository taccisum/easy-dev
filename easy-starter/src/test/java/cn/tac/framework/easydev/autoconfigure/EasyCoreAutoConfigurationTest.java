package cn.tac.framework.easydev.autoconfigure;

import cn.tac.framework.easydev.core.config.EasyCoreProperties;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author tac
 * @since 13/11/2017
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RunWith(SpringRunner.class)
public class EasyCoreAutoConfigurationTest {
    @Autowired
    private EasyCoreProperties easyCoreProperties;

    @Test
    public void testSimply() {
        Assert.assertNotNull(easyCoreProperties);
    }
}
