package cn.tac.framework.easydev.core.util;

import cn.tac.framework.easydev.core.exception.MoreThanOneRootException;
import cn.tac.framework.easydev.core.exception.RootNodeNotFoundException;
import cn.tac.framework.easydev.core.pojo.node.*;
import com.google.common.base.MoreObjects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author tac
 * @since 2.3
 */
public abstract class TreeHelper {
    private static Logger logger = LoggerFactory.getLogger(TreeHelper.class);

    public static <PK, E extends NodeMinStructureAware<PK>> GenericNode<PK, E> build(Collection<E> collection, PK rootId) throws RootNodeNotFoundException, MoreThanOneRootException {
        return build(collection, rootId, 0);
    }

    public static <PK, E extends NodeMinStructureAware<PK>> GenericNode<PK, E> build(Collection<E> collection, PK rootId, int rootLevel) throws RootNodeNotFoundException, MoreThanOneRootException {
        return build(collection, rootId, rootLevel, GenericNode::new);
    }

    public static <PK, E extends NodeMinStructureAware<PK>, N extends Node<PK, E>> N build(Collection<E> collection, PK rootId, NodeInstanceProvider<PK, E, N> provider) throws RootNodeNotFoundException, MoreThanOneRootException {
        return build(collection, rootId, 0, provider, new BuildTreeStatInfo());
    }

    /**
     * @param collection 要转换为树的集合
     * @param rootId     根节点id
     * @param provider   结点实例提供者
     * @throws RootNodeNotFoundException 找不到根节点
     * @throws MoreThanOneRootException  超过一个根节点
     */
    public static <PK, E extends NodeMinStructureAware<PK>, N extends Node<PK, E>> N build(Collection<E> collection, PK rootId, int rootLevel, NodeInstanceProvider<PK, E, N> provider) throws RootNodeNotFoundException, MoreThanOneRootException {
        return build(collection, rootId, rootLevel, provider, new BuildTreeStatInfo());
    }

    private static <PK, E extends NodeMinStructureAware<PK>, N extends Node<PK, E>> N build(Collection<E> collection, PK rootId, int rootLevel, NodeInstanceProvider<PK, E, N> provider, BuildTreeStatInfo stat) throws RootNodeNotFoundException, MoreThanOneRootException {
        logger.debug("build tree frome collection, size: {}, root id: {}, root level: {}", collection.size(), rootId, rootLevel);
        List<E> rootDataList = findById(collection, rootId, stat);
        logger.debug("find root data from collection");
        if (rootDataList == null || rootDataList.size() == 0) {
            logger.debug("can not find any root data");
            throw new RootNodeNotFoundException(rootId);
        }
        if (rootDataList.size() > 1) {
            logger.debug("root data more than one");
            throw new MoreThanOneRootException();
        }
        logger.debug("create root node and add children");
        N root = provider.provide(rootDataList.get(0), null, rootLevel);
        N tree = buildChildren(root, rootLevel, collection, provider, stat);
        logger.debug("recursion stat info: {}", stat);
        return tree;
    }

    private static <PK, E extends NodeMinStructureAware<PK>, N extends Node<PK, E>> N buildChildren(N parent, int parentLevel, Collection<E> collection, NodeInstanceProvider<PK, E, N> provider, BuildTreeStatInfo stat) {
        stat.incrementRecursionTimes();
        for (E item : collection) {
            stat.incrementTraverseTimes();
            //因为是泛型的比较，这里不能用"=="
            if (Objects.equals(item.getParentId(), parent.getId())) {
                int childLevel = parentLevel + 1;
                parent.addChild(buildChildren(provider.provide(item, parent, childLevel), childLevel, collection, provider, stat));
            }
        }
        return parent;
    }

    private static <PK, E extends NodeMinStructureAware<PK>> List<E> findById(Collection<E> collection, PK parentId, BuildTreeStatInfo stat) {
        List<E> result = new ArrayList<>();
        for (E item : collection) {
            stat.incrementTraverseTimes();
            if (Objects.equals(item.getId(), parentId)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * 树构建中的递归统计信息，便于排查性能问题
     */
    static class BuildTreeStatInfo extends RecursionStatInfo {
        //集合遍历次数
        private long traverseTimes;

        public long getTraverseTimes() {
            return traverseTimes;
        }

        public void setTraverseTimes(long traverseTimes) {
            this.traverseTimes = traverseTimes;
        }

        public void incrementTraverseTimes() {
            traverseTimes++;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("recursionTimes", getRecursionTimes())
                    .add("traverseTimes", traverseTimes)
                    .toString();
        }
    }

    /**
     * 选中树的所有指定结点
     *
     * @param tree    目标树
     * @param ids     要选中的结点id
     * @param mapping 状态值映射器
     */
    public static <PK, E extends NodeMinStructureAware<PK>, S, T extends SelectedCapableNode<PK, E, S>> void selectNodes(T tree, Collection<PK> ids, SelectedCapableNode.SelectedFlagMapping<S> mapping) {
        //转换为set，提高查询效率
        Set<PK> idSet;
        if (ids instanceof Set) {
            idSet = (Set<PK>) ids;
        } else {
            idSet = new HashSet<>(ids);
        }
        tree.eachPreOrder((node, args) -> {
            if (idSet.contains(node.getId())) {
                //noinspection unchecked
                ((SelectedCapableNode) node).select(mapping);
            }
        });
    }

    public interface NodeInstanceProvider<PK, E extends NodeMinStructureAware<PK>, N extends Node<PK, E>> {
        N provide(E data, N parent, int rootLevel);
    }
}
