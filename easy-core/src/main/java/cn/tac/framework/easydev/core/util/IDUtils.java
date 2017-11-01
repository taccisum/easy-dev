package cn.tac.framework.easydev.core.util;

import java.util.UUID;

/**
 * ID生成器,用于生成ID
 *
 * @author tac
 * @since 02/08/2017
 */
public abstract class IDUtils {
    public static String UUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
