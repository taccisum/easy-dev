package cn.tac.framework.easydev.autoconfigure.strategy;

import cn.tac.framework.easydev.core.domain.strategy.Strategy;

/**
 * @author tac
 * @since 2.3
 */
public interface PrintStrategy extends Strategy {
    String greeting();
}
