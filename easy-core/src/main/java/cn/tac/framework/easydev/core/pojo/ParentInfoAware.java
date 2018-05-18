package cn.tac.framework.easydev.core.pojo;

/**
 * @author tac
 * @since 2.3
 */
public interface ParentInfoAware<PK> {
    PK getId();

    PK getParentId();
}
