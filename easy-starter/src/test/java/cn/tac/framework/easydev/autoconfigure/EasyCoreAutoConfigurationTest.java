package cn.tac.framework.easydev.autoconfigure;

import cn.tac.framework.easydev.core.config.EasyCoreProperties;
import cn.tac.framework.easydev.core.util.SpringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author tac
 * @since 2.0
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@RunWith(SpringRunner.class)
public class EasyCoreAutoConfigurationTest {
    @Autowired
    private EasyCoreProperties easyCoreProperties;

    @Test
    public void testSimply() {
        Assert.assertNotNull(easyCoreProperties);
        Assert.assertNotNull(SpringUtils.getBean(EasyCoreProperties.class));
    }
}
