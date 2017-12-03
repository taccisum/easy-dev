package cn.tac.framework.easydev.dao.crud;

import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 通用分页查询方法
 *
 * @author tac
 * @since 2.0
 */
public interface Retrieval4PageRepositorySupport<E extends MinEntityStructureAware<PK>, PK>
        extends RetrievalRepositorySupport<E, PK> {

    default PageInfo<E> select4Page(E criteria, int index, int size) {
        return select4Page(criteria, index, size, null, null);
    }

    default PageInfo<E> select4Page(E criteria, int index, int size, Boolean boundary, Boolean containDeleted) {
        PageHelper.startPage(index, size);
        return new PageInfo<>(select(criteria, boundary, containDeleted));
    }

    default PageInfo<E> selectAll4Page(int index, int size) {
        return selectAll4Page(index, size, null, null);
    }

    default PageInfo<E> selectAll4Page(int index, int size, Boolean boundary, Boolean containDeleted) {
        PageHelper.startPage(index, size);
        return new PageInfo<>(selectAll(boundary, containDeleted));
    }
}
