package cn.tac.framework.easydev.dao.crud.config;

import cn.tac.framework.easydev.dao.core.constant.DaoConstant;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tac
 * @since 02/11/2017
 */
@ConfigurationProperties(DaoConstant.CONFIG_PROP_PREFIX)
public class DaoCrudSupportProperties {
    private boolean boundary = true;
    private boolean containDeleted = false;
    private boolean logicDeleted= true;

    public boolean isBoundary() {
        return boundary;
    }

    public void setBoundary(boolean boundary) {
        this.boundary = boundary;
    }

    public boolean isContainDeleted() {
        return containDeleted;
    }

    public void setContainDeleted(boolean containDeleted) {
        this.containDeleted = containDeleted;
    }

    public boolean isLogicDeleted() {
        return logicDeleted;
    }

    public void setLogicDeleted(boolean logicDeleted) {
        this.logicDeleted = logicDeleted;
    }
}
