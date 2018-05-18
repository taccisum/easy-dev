package cn.tac.framework.easydev.core.pojo.node;

import com.google.common.base.MoreObjects;

/**
 * @author tac
 * @since 2.3
 */
public class GenericBooleanSelectedCapableNode<PK, E extends ParentInfoAware<PK>> extends GenericNode<PK, E> implements SelectedCapableNode<PK, E, Boolean> {
    private Boolean isSelected;

    public GenericBooleanSelectedCapableNode(E data, Node<PK, E> parent, long level) {
        super(data, parent, level);
        this.isSelected = false;        //默认不选中
    }

    @Override
    public Boolean getSelected() {
        return isSelected;
    }

    @Override
    public void setSelected(Boolean selected) {
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
