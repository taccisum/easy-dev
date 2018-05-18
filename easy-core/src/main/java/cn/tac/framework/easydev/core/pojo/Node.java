package cn.tac.framework.easydev.core.pojo;

import java.util.Collection;

/**
 * 树型数据结构node
 *
 * @author tac
 * @since 2.3
 */
public interface Node<PK, E extends ParentInfoAware<PK>> {
    PK getId();

    PK getParentId();

    E getData();

    Node<PK, E> getParent();

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
    interface Visitor<PK, E extends ParentInfoAware<PK>> {
        void visit(Node<PK, E> node, Object args);
    }
}
