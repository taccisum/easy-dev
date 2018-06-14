package cn.tac.framework.easydev.dao.core.bean;

/**
 * 运行时数据接口
 *
 * @author tac
 * @since 2.0
 */
public interface RuntimeData4Dao {
    /**
     * 获取当前用户id
     */
    Object userId();

    /**
     * 获取当前租户（当前用户所属租户）id
     */
    Object tenantId();
}
