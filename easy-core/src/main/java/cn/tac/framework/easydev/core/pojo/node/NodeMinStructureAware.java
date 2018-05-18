package cn.tac.framework.easydev.core.pojo.node;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 定义树结点最小结构
 *
 * @author tac
 * @since 2.3
 */
public interface NodeMinStructureAware<PK> {
    @JsonIgnore
    PK getId();

    @JsonIgnore
    PK getParentId();
}
