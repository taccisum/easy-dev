package cn.tac.framework.easydev.dao.core.strategy.id;

/**
 * @author tac
 * @since 2.0
 */
public interface IDGenerator<T> {
    default T generate() {
        return generate(null);
    }

    T generate(Object args);
}
