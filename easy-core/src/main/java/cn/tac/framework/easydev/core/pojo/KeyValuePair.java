package cn.tac.framework.easydev.core.pojo;

import java.util.AbstractMap;

/**
 * @author tac
 * @since 1.0
 */
public class KeyValuePair<KEY, VALUE> extends AbstractMap.SimpleEntry<KEY, VALUE> {
    public KeyValuePair(KEY key, VALUE value) {
        super(key, value);
    }
}
