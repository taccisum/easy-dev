package cn.tac.framework.easydev.core.pojo.node;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.Collection;

/**
 * 树型数据结构node
 *
 * @author tac
 * @since 2.3
 */
@JsonPropertyOrder(value = {"id", "parentId", "level", "data", "selected", "children"})
public interface Node<PK, E extends NodeMinStructureAware<PK>> extends Serializable {
    PK getId();

    PK getParentId();

    E getData();

    @JsonIgnore
    Node<PK, E> getParent();

    Long getLevel();

    Collection<Node<PK, E>> getChildren();

    void addChild(Node<PK, E> child);

    void eachPreOrder(Visitor<PK, E> visitor, Object args);

    default void eachPreOrder(Visitor<PK, E> visitor) {
        eachPreOrder(visitor, null);
    }

    void eachPostOrder(Visitor<PK, E> visitor, Object args);

    default void eachPostOrder(Visitor<PK, E> visitor) {
        eachPostOrder(visitor, null);
    }

    boolean hasChild();

    boolean hasParent();

    @FunctionalInterface
    interface Visitor<PK, E extends NodeMinStructureAware<PK>> {
        void visit(Node<PK, E> node, Object args);
    }
}
