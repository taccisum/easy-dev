package cn.tac.framework.easydev.dao.crud.config;

import cn.tac.framework.easydev.dao.core.constant.DaoConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tac
 * @since 02/11/2017
 */

@ConfigurationProperties(DaoConstant.CONFIG_PROP_PREFIX)
public class DaoCrudSupport {
    private boolean boundaryDefault;

    public boolean isBoundaryDefault() {
        return boundaryDefault;
    }

    public void setBoundaryDefault(boolean boundaryDefault) {
        this.boundaryDefault = boundaryDefault;
    }
}
