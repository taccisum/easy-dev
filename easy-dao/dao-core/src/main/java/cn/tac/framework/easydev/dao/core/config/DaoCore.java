package cn.tac.framework.easydev.dao.core.config;

import cn.tac.framework.easydev.core.constant.GlobalConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * dao配置
 * @author tac
 * @since 01/11/2017
 */
@ConfigurationProperties(GlobalConstant.CONFIG_PROP_PREFIX)
public class DaoCore {
    private String foo1;

    public String getFoo1() {
        return foo1;
    }

    public void setFoo1(String foo1) {
        this.foo1 = foo1;
    }
}
