package cn.tac.framework.easydev.core.util;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

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
    public static final boolean COMPACT_UUID_DEFAULT = true;
    private static String compactEmptyUUID;
    private static String nonCompactEmptyUUID;

    public static String UUID() {
        return UUID(COMPACT_UUID_DEFAULT);
    }

    public static String UUID(boolean compact) {
        if (compact) {
            return UUID.randomUUID().toString().replace("-", "");
        } else {
            return UUID.randomUUID().toString();
        }
    }

    public static String emptyUUID() {
        return emptyUUID(COMPACT_UUID_DEFAULT);
    }

    public static String emptyUUID(boolean compact) {
        if (compact) {
            if (StringUtils.isBlank(compactEmptyUUID)) {
                compactEmptyUUID = buildEmptyUUID(compact);
            }
            return compactEmptyUUID;
        } else {
            if (StringUtils.isBlank(nonCompactEmptyUUID)) {
                nonCompactEmptyUUID = buildEmptyUUID(compact);
            }
            return nonCompactEmptyUUID;
        }
    }

    private static String buildEmptyUUID(boolean compact) {
        List<Integer> ls = Lists.newArrayList(8, 4, 4, 4, 12);
        StringBuilder sb = new StringBuilder();

        for (int i : ls) {
            for (int j : range(i)) {
                sb.append(0);
            }
            if (!compact) {
                sb.append("-");
            }
        }
        String id = sb.toString();
        if (!compact) {
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
