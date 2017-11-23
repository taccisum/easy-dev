package cn.tac.framework.easydev.dao.middle.pojo;

/**
 * 关联信息
 *
 * @author tac
 * @since 2.0
 */
public interface RelevanceInfoAware<LPK, RPK> {
    LPK getSourceId();

    void setSourceId(LPK id);

    RPK getTargetId();

    void setTargetId(RPK id);
}
