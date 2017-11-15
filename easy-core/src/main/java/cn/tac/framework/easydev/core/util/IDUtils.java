package cn.tac.framework.easydev.core.util;

import cn.tac.framework.easydev.core.config.EasyCoreProperties;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * ID生成器,用于生成ID
 *
 * @author tac
 * @since 02/08/2017
 */
public abstract class IDUtils {
    private static String emptyUUID;
    //todo:: 在使用spring boot devtools热部署时，如果修改了compactUUID的配置，可能会出现问题
    private static Boolean compactUUID = null;

    public static String UUID() {
        check();
        if (compactUUID) {
            return UUID.randomUUID().toString().replace("-", "");
        } else {
            return UUID.randomUUID().toString();
        }
    }

    public static String emptyUUID() {
        if (emptyUUID == null) {
            emptyUUID = buildEmptyUUID();
        }
        return emptyUUID;
    }

    private static void check() {
        if (compactUUID == null) {
            EasyCoreProperties easyCoreProperties = SpringUtils.getBean(EasyCoreProperties.class);
            compactUUID = easyCoreProperties == null ? true : easyCoreProperties.isCompactUUID();
        }
    }

    private static String buildEmptyUUID() {
        check();
        List<Integer> ls = Lists.newArrayList(8, 4, 4, 16);
        StringBuilder sb = new StringBuilder();

        for (int i : ls) {
            for (int j : range(i)) {
                sb.append(0);
            }
            if (compactUUID) {
                sb.append("-");
            }
        }
        String id = sb.toString();
        if (compactUUID) {
            id = id.substring(0, id.length() - 1);
        }
        return id;
    }

    private static List<Integer> range(int count) {
        List<Integer> ls = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            ls.add(i);
        }
        return ls;
    }
}
