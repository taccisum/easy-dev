package cn.tac.framework.easydev.dao.core.strategy.deletedflag;

/**
 * @author tac
 * @since 2.0
 */
public interface DeletedFlagMapping<T> {
    T getEnableFlag();
    T getDisableFlag();
}
