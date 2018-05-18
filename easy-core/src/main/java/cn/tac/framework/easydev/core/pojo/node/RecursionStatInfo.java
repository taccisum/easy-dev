package cn.tac.framework.easydev.core.pojo.node;

import com.google.common.base.MoreObjects;

/**
 * 递归统计信息
 *
 * @author tac
 * @since 2.3
 */
public class RecursionStatInfo {
    private long recursionTimes = 0;

    public long getRecursionTimes() {
        return recursionTimes;
    }

    public void setRecursionTimes(long recursionTimes) {
        this.recursionTimes = recursionTimes;
    }

    public void incrementRecursionTimes() {
        recursionTimes++;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("recursionTimes", recursionTimes)
                .toString();
    }
}
