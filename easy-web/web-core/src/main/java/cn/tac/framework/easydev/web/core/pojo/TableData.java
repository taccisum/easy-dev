package cn.tac.framework.easydev.web.core.pojo;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tac
 * @since 2.0
 */
public class TableData<T> {
    @ApiModelProperty("分页前的总条目数")
    private Long total;
    @ApiModelProperty("分页后的数据")
    private List<T> rows;

    public TableData() {
    }

    public Long getTotal() {
        return this.total == null ? Long.valueOf(0L) : this.total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        if (this.rows == null) {
            this.rows = new ArrayList<>();
        }

        return this.rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public void add(T row) {
        this.getRows().add(row);
    }

    public void addAll(List<T> rows) {
        this.getRows().addAll(rows);
    }

    public static <T> TableData<T> empty() {
        TableData<T> o = new TableData<>();
        o.setTotal(0L);
        o.setRows(new ArrayList<>());
        return o;
    }
}
