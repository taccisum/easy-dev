package cn.tac.framework.easydev.service.crud.pojo;

import java.util.ArrayList;

/**
 * 查询条件
 *
 * @author tac
 * @since 2.0
 */
public class QueryCriteria extends ArrayList<QueryGroup> {
    private Integer index;
    private Integer size;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
