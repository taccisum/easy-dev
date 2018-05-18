package cn.tac.framework.easydev.core.pojo;

import com.google.common.base.MoreObjects;

/**
 * @author tac
 * @since 2.3
 */
public class GenericIntegerSelectedCapableNode<PK, E extends ParentInfoAware<PK>> extends GenericNode<PK, E> implements SelectedCapableNode<PK, E, Integer> {
    private Integer isSelected;

    public GenericIntegerSelectedCapableNode(E data, Node<PK, E> parent, long level) {
        super(data, parent, level);
        this.isSelected = IntegerSelectedFlagMapping.instance().unselectedState();        //默认不选中
    }

    @Override
    public Integer getSelected() {
        return isSelected;
    }

    @Override
    public void setSelected(Integer selected) {
        isSelected = selected;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("data", getData())
                .add("parentId", getParentId())
                .add("level", getLevel())
                .add("children", getChildren())
                .add("isSelected", isSelected)
                .toString();
    }
}
