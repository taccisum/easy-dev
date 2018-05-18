package cn.tac.framework.easydev.core.pojo;

import java.util.Objects;

/**
 * 可以被选中的node
 *
 * @author tac
 * @since 2.3
 */
public interface SelectedCapableNode<PK, E extends ParentInfoAware<PK>, S> extends Node<PK, E> {
    S getSelected();

    void setSelected(S selected);

    /**
     * 选中此节点
     */
    default void select(SelectedFlagMapping<S> mapping) {
        selectParent(this, mapping, true);
        selectChildren(this, mapping);
    }

    static <PK, E extends ParentInfoAware<PK>, S, N extends SelectedCapableNode<PK, E, S>> void selectParent(N node, SelectedFlagMapping<S> mapping, boolean isSelf) {
        S state = mapping.selectedState();
        if (!isSelf) {
            for (Node<PK, E> child : node.getChildren()) {
                if (!Objects.equals(((SelectedCapableNode) child).getSelected(), mapping.selectedState())) {
                    //如果有任何一个子节点不是处于选中状态，则将父结点置于第三态
                    state = mapping.middleState();
                    break;
                }
            }
        }
        //先选中当前结点，再往上选中其父结点，这个顺序不能颠倒
        node.setSelected(state);
        if (node.hasParent()) {
            //noinspection unchecked
            selectParent((N) node.getParent(), mapping, false);
        }
    }

    static <PK, E extends ParentInfoAware<PK>, S, N extends SelectedCapableNode<PK, E, S>> void selectChildren(N node, SelectedFlagMapping<S> mapping) {
        if (node.hasChild()) {
            for (Node<PK, E> child : node.getChildren()) {
                //noinspection unchecked
                selectChildren((N) child, mapping);
            }
        }
        node.setSelected(mapping.selectedState());
    }

    /**
     * 选中状态与状态值的映射接口
     */
    interface SelectedFlagMapping<S> {
        S selectedState();

        S unselectedState();

        S middleState();
    }

    class BooleanSelectedFlagMapping implements SelectedFlagMapping<Boolean> {
        private static BooleanSelectedFlagMapping instance = new BooleanSelectedFlagMapping();

        BooleanSelectedFlagMapping() {
        }

        public static BooleanSelectedFlagMapping instance() {
            if (instance == null) {
                instance = new BooleanSelectedFlagMapping();
            }
            return instance;
        }

        @Override
        public Boolean selectedState() {
            return true;
        }

        @Override
        public Boolean unselectedState() {
            return false;
        }

        @Override
        public Boolean middleState() {
            return true;
        }
    }

    class IntegerSelectedFlagMapping implements SelectedFlagMapping<Integer> {
        private static IntegerSelectedFlagMapping instance = new IntegerSelectedFlagMapping();

        IntegerSelectedFlagMapping() {
        }

        public static IntegerSelectedFlagMapping instance() {
            if (instance == null) {
                instance = new IntegerSelectedFlagMapping();
            }
            return instance;
        }

        @Override
        public Integer selectedState() {
            return 1;
        }

        @Override
        public Integer unselectedState() {
            return 0;
        }

        @Override
        public Integer middleState() {
            return 2;
        }
    }
}
