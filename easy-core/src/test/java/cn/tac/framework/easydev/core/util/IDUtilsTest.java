package cn.tac.framework.easydev.core.util;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author tac
 * @since 01/11/2017
 */
public class IDUtilsTest {
    @Test
    public void UUID() {
        Assert.assertEquals(32, IDUtils.UUID().length());
    }
    @Test
    public void emptyUUID() throws Exception {
        Assert.assertEquals("00000000000000000000000000000000", IDUtils.emptyUUID());
        Assert.assertEquals(32, IDUtils.emptyUUID().length());
    }
}
