package cn.tac.framework.easydev.dao.core.pojo;

import cn.tac.framework.easydev.dao.core.util.EntityUtils;

/**
 * 提供初始化一个新的实体某些字段的默认值
 *
 * @author tac
 * @since 2.0
 */
public interface InitializingEntity {
    /**
     * 初始化实体，一般应交由{@link EntityUtils}的静态方法来完成
     */
    void init();
}
