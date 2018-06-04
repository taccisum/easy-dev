package cn.tac.framework.easydev.dao.core.pojo;

import cn.tac.framework.easydev.dao.core.strategy.deletedflag.DeletedFlagMapping;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 逻辑删除标识
 *
 * @author tac
 * @since 2.0
 */
public interface DeletedFlagAware<T> {
    T getDeletedFlag();
    void setDeletedFlag(T flag);

    @JsonIgnore
    DeletedFlagMapping<T> deletedFlagMapping();
}
