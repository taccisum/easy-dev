package cn.tac.framework.easydev.core.pojo;

import com.google.common.base.MoreObjects;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 树型数据结构结点
 *
 * @author tac
 * @since 2.3
 */
public class GenericNode<PK, E extends ParentInfoAware<PK>> implements Node<PK, E> {
    private E data;
    private Node<PK, E> parent;
    private Long level;
    private Collection<Node<PK, E>> children;

    public GenericNode(E data, Node<PK, E> parent, long level) {
        this.data = data;
        this.parent = parent;
        this.level = level;
    }

    @Override
    public PK getId() {
        return data.getId();
    }

    @Override
    public PK getParentId() {
        return data.getParentId();
    }

    @Override
    public E getData() {
        return data;
    }

    @Override
    public Node<PK, E> getParent() {
        return parent;
    }

    public Long getLevel() {
        return level;
    }

    @Override
    public Collection<Node<PK, E>> getChildren() {
        return children;
    }

    @Override
    public void addChild(Node<PK, E> child) {
        if (children == null) {
            children = initChildren();
        }
        children.add(child);
    }

    @Override
    public void eachPreOrder(Visitor<PK, E> visitor, Object args) {
        visitor.visit(this, args);
        eachChildren(visitor, args, true);
    }

    @Override
    public void eachPostOrder(Visitor<PK, E> visitor, Object args) {
        eachChildren(visitor, args, false);
        visitor.visit(this, args);
    }

    @Override
    public boolean hasChild() {
        return CollectionUtils.isNotEmpty(getChildren());
    }

    @Override
    public boolean hasParent() {
        return parent != null;
    }

    protected void eachChildren(Visitor<PK, E> visitor, Object args, boolean pre) {
        if (hasChild()) {
            if (pre) {
                for (Node<PK, E> child : children) {
                    child.eachPreOrder(visitor, args);
                }
            } else {
                for (Node<PK, E> child : children) {
                    child.eachPostOrder(visitor, args);
                }
            }
        }
    }

    protected Collection<Node<PK, E>> initChildren() {
        return new ArrayList<>();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("data", data)
                .add("parent", parent)
                .add("level", level)
                .add("children", children)
                .toString();
    }
}
