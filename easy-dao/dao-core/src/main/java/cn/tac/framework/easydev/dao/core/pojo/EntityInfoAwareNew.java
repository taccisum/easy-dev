package cn.tac.framework.easydev.dao.core.pojo;

/**
 * 与实体本身相关的信息记录
 *
 * @author tac
 * @since 2.3
 */
public interface EntityInfoAwareNew<T> extends CreatorAwareNew<T>, CreationTimeAware, UpdatorAwareNew<T>, UpdationTimeAware {
}
