package cn.tac.framework.easydev.core.util;

import cn.tac.framework.easydev.core.exception.MoreThanOneRootException;
import cn.tac.framework.easydev.core.exception.RootNodeNotFoundException;
import cn.tac.framework.easydev.core.pojo.*;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Sets;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;

import java.util.ArrayList;
import java.util.HashMap;
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

    @Test
    public void selectNodes() throws Exception {
        SelectedCapableNode.IntegerSelectedFlagMapping mapping = SelectedCapableNode.IntegerSelectedFlagMapping.instance();
        List<Foo> ls = new ArrayList<>();
        /**
         * 测试数据结构如下
         *    1
         *    |
         *    2
         *    |
         *    3
         */
        ls.add(new Foo(1L, null));
        ls.add(new Foo(2L, 1L));
        ls.add(new Foo(3L, 2L));
        //测试选中结点1
        GenericIntegerSelectedCapableNode<Long, Foo> tree = TreeHelper.build(ls, 1L, GenericIntegerSelectedCapableNode::new);
        TreeHelper.selectNodes(tree, Sets.newHashSet(1L), mapping);
        HashMap<Long, Integer> selectedStateMap = extractSelectedStateMap(tree);
        assertThat(selectedStateMap.get(1L)).isEqualTo(mapping.selectedState());
        assertThat(selectedStateMap.get(2L)).isEqualTo(mapping.selectedState());
        assertThat(selectedStateMap.get(3L)).isEqualTo(mapping.selectedState());

        //测试选中结点2
        GenericIntegerSelectedCapableNode<Long, Foo> tree1 = TreeHelper.build(ls, 1L, GenericIntegerSelectedCapableNode::new);
        TreeHelper.selectNodes(tree1, Sets.newHashSet(2L), mapping);
        HashMap<Long, Integer> selectedStateMap1 = extractSelectedStateMap(tree1);
        assertThat(selectedStateMap1.get(1L)).isEqualTo(mapping.selectedState());
        assertThat(selectedStateMap1.get(2L)).isEqualTo(mapping.selectedState());
        assertThat(selectedStateMap1.get(3L)).isEqualTo(mapping.selectedState());

        //测试选中结点3
        GenericIntegerSelectedCapableNode<Long, Foo> tree2 = TreeHelper.build(ls, 1L, GenericIntegerSelectedCapableNode::new);
        TreeHelper.selectNodes(tree2, Sets.newHashSet(3L), mapping);
        HashMap<Long, Integer> selectedStateMap2 = extractSelectedStateMap(tree2);
        assertThat(selectedStateMap2.get(1L)).isEqualTo(mapping.selectedState());
        assertThat(selectedStateMap2.get(2L)).isEqualTo(mapping.selectedState());
        assertThat(selectedStateMap2.get(3L)).isEqualTo(mapping.selectedState());

        //测试选中多个结点
        GenericIntegerSelectedCapableNode<Long, Foo> tree3 = TreeHelper.build(ls, 1L, GenericIntegerSelectedCapableNode::new);
        TreeHelper.selectNodes(tree3, Sets.newHashSet(1L, 2L, 3L), mapping);
        HashMap<Long, Integer> selectedStateMap3 = extractSelectedStateMap(tree3);
        assertThat(selectedStateMap3.get(1L)).isEqualTo(mapping.selectedState());
        assertThat(selectedStateMap3.get(2L)).isEqualTo(mapping.selectedState());
        assertThat(selectedStateMap3.get(3L)).isEqualTo(mapping.selectedState());
    }

    @Test
    public void selectNodes1() throws Exception {
        SelectedCapableNode.IntegerSelectedFlagMapping mapping = SelectedCapableNode.IntegerSelectedFlagMapping.instance();
        List<Foo> ls = new ArrayList<>();
        /**
         * 测试数据结构如下
         *    1
         *    | \
         *    2  3
         *       |
         *       4
         */
        ls.add(new Foo(1L, null));
        ls.add(new Foo(2L, 1L));
        ls.add(new Foo(3L, 1L));
        ls.add(new Foo(4L, 3L));
        //测试选中结点1
        GenericIntegerSelectedCapableNode<Long, Foo> tree = TreeHelper.build(ls, 1L, GenericIntegerSelectedCapableNode::new);
        TreeHelper.selectNodes(tree, Sets.newHashSet(1L), mapping);
        HashMap<Long, Integer> selectedStateMap = extractSelectedStateMap(tree);
        assertThat(selectedStateMap.get(1L)).isEqualTo(mapping.selectedState());
        assertThat(selectedStateMap.get(2L)).isEqualTo(mapping.selectedState());
        assertThat(selectedStateMap.get(3L)).isEqualTo(mapping.selectedState());
        assertThat(selectedStateMap.get(3L)).isEqualTo(mapping.selectedState());

        //测试选中结点2
        GenericIntegerSelectedCapableNode<Long, Foo> tree1 = TreeHelper.build(ls, 1L, GenericIntegerSelectedCapableNode::new);
        TreeHelper.selectNodes(tree1, Sets.newHashSet(2L), mapping);
        HashMap<Long, Integer> selectedStateMap1 = extractSelectedStateMap(tree1);
        assertThat(selectedStateMap1.get(1L)).isEqualTo(mapping.middleState());
        assertThat(selectedStateMap1.get(2L)).isEqualTo(mapping.selectedState());
        assertThat(selectedStateMap1.get(3L)).isEqualTo(mapping.unselectedState());
        assertThat(selectedStateMap1.get(4L)).isEqualTo(mapping.unselectedState());

        //测试选中结点3
        GenericIntegerSelectedCapableNode<Long, Foo> tree2 = TreeHelper.build(ls, 1L, GenericIntegerSelectedCapableNode::new);
        TreeHelper.selectNodes(tree2, Sets.newHashSet(3L), mapping);
        HashMap<Long, Integer> selectedStateMap2 = extractSelectedStateMap(tree2);
        assertThat(selectedStateMap2.get(1L)).isEqualTo(mapping.middleState());
        assertThat(selectedStateMap2.get(2L)).isEqualTo(mapping.unselectedState());
        assertThat(selectedStateMap2.get(3L)).isEqualTo(mapping.selectedState());
        assertThat(selectedStateMap2.get(4L)).isEqualTo(mapping.selectedState());

        //测试选中结点4
        GenericIntegerSelectedCapableNode<Long, Foo> tree3 = TreeHelper.build(ls, 1L, GenericIntegerSelectedCapableNode::new);
        TreeHelper.selectNodes(tree3, Sets.newHashSet(3L), mapping);
        HashMap<Long, Integer> selectedStateMap3 = extractSelectedStateMap(tree3);
        assertThat(selectedStateMap3.get(1L)).isEqualTo(mapping.middleState());
        assertThat(selectedStateMap3.get(2L)).isEqualTo(mapping.unselectedState());
        assertThat(selectedStateMap3.get(3L)).isEqualTo(mapping.selectedState());
        assertThat(selectedStateMap3.get(4L)).isEqualTo(mapping.selectedState());

        //测试选中结点2,3
        GenericIntegerSelectedCapableNode<Long, Foo> tree4 = TreeHelper.build(ls, 1L, GenericIntegerSelectedCapableNode::new);
        TreeHelper.selectNodes(tree4, Sets.newHashSet(2L, 3L), mapping);
        HashMap<Long, Integer> selectedStateMap4 = extractSelectedStateMap(tree4);
        assertThat(selectedStateMap4.get(1L)).isEqualTo(mapping.selectedState());
        assertThat(selectedStateMap4.get(2L)).isEqualTo(mapping.selectedState());
        assertThat(selectedStateMap4.get(3L)).isEqualTo(mapping.selectedState());
        assertThat(selectedStateMap4.get(4L)).isEqualTo(mapping.selectedState());
    }

    private HashMap<Long, Integer> extractSelectedStateMap(GenericIntegerSelectedCapableNode<Long, Foo> tree) {
        HashMap<Long, Integer> selectedStateMap = new HashMap<>();
        tree.eachPreOrder((node, args) -> {
            GenericIntegerSelectedCapableNode<Long, Foo> n = (GenericIntegerSelectedCapableNode) node;
            selectedStateMap.put(n.getId(), n.getSelected());
        });
        return selectedStateMap;
    }


    private void debug(Node<Long, Foo> tree) {
        tree.eachPreOrder((node, args) -> {
            System.out.println(node);
        });
    }

    private static class Foo implements ParentInfoAware<Long> {
        private Long id;
        private Long parentId;
        private Boolean isSelected;

        public Boolean getSelected() {
            return isSelected;
        }

        public void setSelected(Boolean selected) {
            isSelected = selected;
        }

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
