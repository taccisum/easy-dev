package cn.tac.framework.easydev.core.util;

import cn.tac.framework.easydev.core.exception.MoreThanOneRootException;
import cn.tac.framework.easydev.core.exception.RootNodeNotFoundException;
import cn.tac.framework.easydev.core.pojo.Node;
import cn.tac.framework.easydev.core.pojo.ParentInfoAware;
import com.google.common.base.MoreObjects;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author tac
 * @since 2.3
 */
public class TreeHelperTest {
    @Rule
    public OutputCapture capture = new OutputCapture();

    @Test
    public void build() throws Exception {
        List<Foo> ls = new ArrayList<>();
        /**
         * 测试数据结构如下
         *         1
         *      /  |  \
         *    2    4    5
         *   /    / \
         *  3    6   7
         *      /
         *     8
         */
        ls.add(new Foo(1L, null));
        ls.add(new Foo(2L, 1L));
        ls.add(new Foo(3L, 2L));
        ls.add(new Foo(4L, 1L));
        ls.add(new Foo(5L, 1L));
        ls.add(new Foo(6L, 4L));
        ls.add(new Foo(7L, 4L));
        ls.add(new Foo(8L, 6L));
        Node<Long, Foo> tree = TreeHelper.build(ls, 1L);
        StringBuilder sb = new StringBuilder();
        tree.eachPreOrder((node, args) -> sb.append(node.getId()));
        assertThat(sb.toString()).isEqualTo("12346875");
        StringBuilder sb1 = new StringBuilder();
        tree.eachPostOrder((node, args) -> sb1.append(node.getId()));
        assertThat(sb1.toString()).isEqualTo("32867451");
    }

    @Test
    public void buildWhenExistRepeatNode() throws Exception {
        List<Foo> ls = new ArrayList<>();
        /**
         * 测试数据结构如下
         *         1
         *      /  |  \
         *    2    4    5
         *         |
         *         2
         */
        ls.add(new Foo(1L, null));
        ls.add(new Foo(2L, 1L));
        ls.add(new Foo(4L, 1L));
        ls.add(new Foo(2L, 4L));
        ls.add(new Foo(5L, 1L));
        Node<Long, Foo> tree = TreeHelper.build(ls, 1L);
        StringBuilder sb = new StringBuilder();
        tree.eachPreOrder((node, args) -> sb.append(node.getId()));
        assertThat(sb.toString()).isEqualTo("12425");
        StringBuilder sb1 = new StringBuilder();
        tree.eachPostOrder((node, args) -> sb1.append(node.getId()));
        assertThat(sb1.toString()).isEqualTo("22451");
    }

    @Test
    public void buildWhenExistFloatNode() throws Exception {
        List<Foo> ls = new ArrayList<>();
        /**
         * 测试数据结构如下
         *         1
         *      /  |  \
         *    2    4    5
         *         |
         *         2
         *
         *   6
         *   | \
         *   7  8
         */
        ls.add(new Foo(1L, null));
        ls.add(new Foo(2L, 1L));
        ls.add(new Foo(4L, 1L));
        ls.add(new Foo(2L, 4L));
        ls.add(new Foo(5L, 1L));
        ls.add(new Foo(6L, null));
        ls.add(new Foo(7L, 6L));
        ls.add(new Foo(8L, 6L));
        Node<Long, Foo> tree = TreeHelper.build(ls, 1L);
        StringBuilder sb = new StringBuilder();
        tree.eachPreOrder((node, args) -> sb.append(node.getId()));
        assertThat(sb.toString()).isEqualTo("12425");
        StringBuilder sb1 = new StringBuilder();
        tree.eachPostOrder((node, args) -> sb1.append(node.getId()));
        assertThat(sb1.toString()).isEqualTo("22451");
    }

    @Test(expected = MoreThanOneRootException.class)
    public void buildWhenMoreThanOneRoot() throws Exception {
        List<Foo> ls = new ArrayList<>();
        ls.add(new Foo(1L, null));
        ls.add(new Foo(1L, 0L));
        TreeHelper.build(ls, 1L, 0);
    }

    @Test(expected = RootNodeNotFoundException.class)
    public void buildWhenRootNotFound() throws Exception {
        List<Foo> ls = new ArrayList<>();
        ls.add(new Foo(1L, null));
        TreeHelper.build(ls, 0L, 0);
    }

    private void debug(Node<Long, Foo> tree) {
        tree.eachPreOrder((node, args) -> {
            System.out.println(node.getData());
        });
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
