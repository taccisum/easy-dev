package cn.tac.framework.easydev.dao.core.strategy.id;

/**
 * @author tac
 * @since 15/11/2017
 */
public interface IDGenerator<T> {
    T generate(Object args);
}
