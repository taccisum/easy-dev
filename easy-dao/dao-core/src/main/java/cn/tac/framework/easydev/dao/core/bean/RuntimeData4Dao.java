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
    String userId();

    /**
     * 获取当前组织（当前用户所属组织）id
     */
    String organizationId();
}
