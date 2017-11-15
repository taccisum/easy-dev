package cn.tac.framework.easydev.dao.core.strategy.deletedflag;

/**
 * @author tac
 * @since 15/11/2017
 */
public interface DeletedFlagMapping<T> {
    T getEnableFlag();
    T getDisableFlag();
}
