package cn.tac.framework.easydev.autoconfigure;

import cn.tac.framework.easydev.dao.core.bean.RuntimeData4Dao;
import cn.tac.framework.easydev.dao.core.config.DaoCoreProperties;
import cn.tac.framework.easydev.dao.core.util.EntityUtils;
import cn.tac.framework.easydev.dao.crud.config.DaoCrudSupportProperties;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author tac
 * @since 2.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = EasyDaoAutoConfigurationTest.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@SpringBootConfiguration
@ImportAutoConfiguration(EasyDaoAutoConfiguration.class)
public class EasyDaoAutoConfigurationTest {
    @Autowired private DaoCoreProperties daoCoreProperties;
    @Autowired private DaoCrudSupportProperties daoCrudSupportProperties;
    @Autowired private RuntimeData4Dao runtimeData4Dao;
    @Autowired private EntityUtils entityUtils;

    @Test
    public void testSimply() {
        Assert.assertNotNull(daoCoreProperties);
        Assert.assertNotNull(daoCrudSupportProperties);
        Assert.assertNotNull(runtimeData4Dao);
        Assert.assertNotNull(entityUtils);
    }

    @Test
    public void testDefaultRuntimeData4Dao() {
        Assert.assertTrue(StringUtils.isNoneBlank(runtimeData4Dao.userId()));
    }

}
