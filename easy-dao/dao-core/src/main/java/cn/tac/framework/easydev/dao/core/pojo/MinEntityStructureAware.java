package cn.tac.framework.easydev.dao.core.pojo;

import java.io.Serializable;

/**
 * 感知实体的最小结构，所有的实体都实现此接口
 *
 * @author tac
 * @since 2.0
 */
public interface MinEntityStructureAware<PK> extends PrimaryKeyAware<PK>, Serializable {
}
