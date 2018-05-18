package cn.tac.framework.easydev.core.pojo.node;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author tac
 * @since 2.3
 */
public interface ParentInfoAware<PK> {
    @JsonIgnore
    PK getId();

    @JsonIgnore
    PK getParentId();
}
