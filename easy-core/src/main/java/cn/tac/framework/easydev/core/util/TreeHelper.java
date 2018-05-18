package cn.tac.framework.easydev.core.util;

import cn.tac.framework.easydev.core.exception.MoreThanOneRootException;
import cn.tac.framework.easydev.core.exception.RootNodeNotFoundException;
import cn.tac.framework.easydev.core.pojo.GenericNode;
import cn.tac.framework.easydev.core.pojo.Node;
import cn.tac.framework.easydev.core.pojo.ParentInfoAware;
import cn.tac.framework.easydev.core.pojo.RecursionStatInfo;
import com.google.common.base.MoreObjects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @author tac
 * @since 2.3
 */
public abstract class TreeHelper {
    private static Logger logger = LoggerFactory.getLogger(TreeHelper.class);

    public static <PK, E extends ParentInfoAware<PK>> Node<PK, E> build(Collection<E> collection, PK rootId) throws RootNodeNotFoundException, MoreThanOneRootException {
        return build(collection, rootId, 0);
    }

    /**
     * @throws RootNodeNotFoundException 找不到根节点
     * @throws MoreThanOneRootException  超过一个根节点
     */
    public static <PK, E extends ParentInfoAware<PK>> Node<PK, E> build(Collection<E> collection, PK rootId, int rootLevel) throws RootNodeNotFoundException, MoreThanOneRootException {
        return build(collection, rootId, rootLevel, new BuildTreeStatInfo());
    }

    static <PK, E extends ParentInfoAware<PK>> Node<PK, E> build(Collection<E> collection, PK rootId, int rootLevel, BuildTreeStatInfo stat) throws RootNodeNotFoundException, MoreThanOneRootException {
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
        Node root = new GenericNode<>(rootDataList.get(0), null, rootLevel);
        Node tree = buildChildren(root, rootLevel, collection, stat);
        logger.debug("recursion stat info: {}", stat);
        return tree;
    }

    private static <PK, E extends ParentInfoAware<PK>> Node<PK, E> buildChildren(Node<PK, E> parent, int parentLevel, Collection<E> collection, BuildTreeStatInfo stat) {
        stat.incrementRecursionTimes();
        for (E item : collection) {
            stat.incrementTraverseTimes();
            //因为是泛型的比较，这里不能用"=="
            if (Objects.equals(item.getParentId(), parent.getId())) {
                int childLevel = parentLevel + 1;
                parent.addChild(buildChildren(new GenericNode<>(item, parent, childLevel), childLevel, collection, stat));
            }
        }
        return parent;
    }

    private static <PK, E extends ParentInfoAware<PK>> List<E> findById(Collection<E> collection, PK parentId, BuildTreeStatInfo stat) {
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
}
