package cn.tac.framework.easydev.core.util;

import cn.tac.framework.easydev.core.pojo.node.ParentInfoAware;
import com.google.common.base.MoreObjects;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tac
 * @since 2.3
 */
@Ignore
public class TreeHelperPerformanceTest {
    @Test
    public void buildPerformanceTestWhenTreeALinkedList() throws Exception {
        List<Foo> ls = new ArrayList<>();
        /**
         * 测试数据结构如下
         *   1
         *   |
         *   2
         *   |
         *   …
         *   |
         *  n-1
         *   |
         *   n
         */
        for (long i = 0; i < 1000; i++) {
            if (i == 0) {
                ls.add(new Foo(i, null));
            } else {
                ls.add(new Foo(i, i - 1));
            }
        }
        StopWatch sw = new StopWatch();
        sw.start();
        int times = 500;
        for (int i = 0; i < times; i++) {
            TreeHelper.build(ls, 1L, 0);
        }
        sw.stop();
        System.out.println(String.format("总用时：%dms，平均用时：%dms", sw.getTime(), sw.getTime() / times));
        //链表情况下，优化前3800ms左右，优化后3500ms左右，差别不大
    }

    @Test
    public void buildPerformanceTest() throws Exception {
        List<Foo> ls = new ArrayList<>();
        /**
         * 测试数据结构如下
         *           1
         *     /  /  |  \  \  \
         *   2   3   4   5  …   n
         */
        for (long i = 0; i < 1000; i++) {
            if (i == 0) {
                ls.add(new Foo(i, null));
            } else {
                ls.add(new Foo(i, 0L));
            }
        }
        StopWatch sw = new StopWatch();
        sw.start();
        int times = 500;
        for (int i = 0; i < times; i++) {
            TreeHelper.build(ls, 1L, 0);
        }
        sw.stop();
        System.out.println(String.format("总用时：%dms，平均用时：%dms", sw.getTime(), sw.getTime() / times));
    }

    private static class Foo implements ParentInfoAware<Long> {
        private Long id;
        private Long parentId;

        public Foo(Long id, Long parentId) {
            this.id = id;
            this.parentId = parentId;
        }

        @Override
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        @Override
        public Long getParentId() {
            return parentId;
        }

        public void setParentId(Long parentId) {
            this.parentId = parentId;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("id", id)
                    .add("parentId", parentId)
                    .toString();
        }
    }
}
