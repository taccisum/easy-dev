package cn.tac.framework.easydev.service.crud.pojo;

import cn.tac.framework.easydev.service.crud.constant.Relevant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tac
 * @since 17/11/2017
 */
public class QueryGroup extends ArrayList<QueryItem> {
    private List<QueryItem> items;
    private Relevant relevant;

    public List<QueryItem> getItems() {
        return items;
    }

    public void setItems(List<QueryItem> items) {
        this.items = items;
    }

    public Relevant getRelevant() {
        return relevant;
    }

    public void setRelevant(Relevant relevant) {
        this.relevant = relevant;
    }
}
