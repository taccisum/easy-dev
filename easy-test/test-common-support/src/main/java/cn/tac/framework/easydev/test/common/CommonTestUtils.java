package cn.tac.framework.easydev.test.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tac
 * @since 2.0
 */
public abstract class CommonTestUtils {
    /**
     * 生成一个[start, end)的数组
     *
     * @param start 起始值
     * @param end   结束值
     */
    public static List<Integer> range(int start, int end) {
        if (start >= end) {
            throw new IllegalArgumentException("start num can not great than end num");
        }
        List<Integer> range = new ArrayList<>();
        for (int a = start; a < end; a++) {
            range.add(a);
        }
        return range;
    }

    /**
     * 生成一个[0, count)的数组
     *
     * @param count 数组长度
     */
    public static List<Integer> range(int count) {
        return range(0, count);
    }

    /**
     * 生成一行纯字符组成的分割线，使用默认长度
     */
    public static void divider() {
        divider(50);
    }

    /**
     * 生成一行纯字符组成的分割线
     *
     * @param length 分割线长度
     */
    public static void divider(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i : range(length)) {
            sb.append("-");
        }
        System.out.println(sb.toString());
    }

    /**
     * 重复执行动作
     *
     * @param action 需要执行的操作，推荐使用lambda表达式
     * @param times  重复的次数[0, times)
     */
    public static void repeat(Action action, int times) {
        for (int i : range(times)) {
            action.execute(i);
        }
    }

    @FunctionalInterface
    public interface Action {
        void execute(int i);
    }
}
