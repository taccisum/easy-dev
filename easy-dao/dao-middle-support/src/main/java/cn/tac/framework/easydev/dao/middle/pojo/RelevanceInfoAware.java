package cn.tac.framework.easydev.dao.middle.pojo;

/**
 * @author tac
 * @since 17/11/2017
 */
public interface RelevanceInfoAware<LPK, RPK> {
    LPK getSourceId();

    void setSourceId(LPK id);

    RPK getTargetId();

    void setTargetId(RPK id);
}
