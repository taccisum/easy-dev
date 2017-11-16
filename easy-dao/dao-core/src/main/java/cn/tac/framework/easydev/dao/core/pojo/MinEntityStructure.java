package cn.tac.framework.easydev.dao.core.pojo;

import java.io.Serializable;

/**
 * 定义实体最小结构，所有的实体都应该实现此接口
 *
 * @author tac
 * @since 15/11/2017
 */
public interface MinEntityStructure<PK> extends PrimaryKeyAware<PK>, Serializable {
}
