package cn.tac.framework.easydev.dao.core.pojo;

/**
 * 与实体本身相关的信息记录
 *
 * @author tac
 * @since 2.0
 */
public interface EntityInfoAware<UPK> extends CreatorAware<UPK>, CreationTimeAware, UpdatorAware<UPK>, UpdationTimeAware {
}
