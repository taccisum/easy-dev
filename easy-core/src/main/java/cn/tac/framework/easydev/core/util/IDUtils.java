package cn.tac.framework.easydev.core.util;

import java.util.UUID;

/**
 * ID生成器,用于生成ID
 *
 * @author tac
 * @since 02/08/2017
 */
public abstract class IDUtils {
    private static String emptyUUID;

    public static String UUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String emptyUUID() {
        if (emptyUUID == null) {
            emptyUUID = build();
        }
        return emptyUUID;
    }

    private static String build() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            sb.append(0);
        }
        return sb.toString();
    }
}
