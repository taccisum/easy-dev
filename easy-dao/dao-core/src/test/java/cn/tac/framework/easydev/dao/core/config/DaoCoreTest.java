package cn.tac.framework.easydev.dao.core.config;

import cn.tac.framework.easydev.dao.core.app.Application4UnitTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author tac
 * @since 01/11/2017
 */
@SpringBootTest(classes = Application4UnitTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class DaoCoreTest {
    @Autowired
    private DaoCore daoCore;

    @Test
    public void testSimply() {
        Assert.assertNotNull(daoCore);
    }
}
