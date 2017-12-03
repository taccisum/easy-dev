package pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiParam;

/**
 * 定义前端分页数据请求通用数据格式
 *
 * @author tac
 * @since 1.0
 */
public abstract class PaginationRequest {
    private static final int DEF_INDEX = 1;
    private static final int DEF_SIZE = 20;
    @ApiParam(value = "分页开始页数", defaultValue = "1")
    private Integer index;
    @ApiParam(value = "分页每页大小", defaultValue = "20")
    private Integer size;

    @JsonIgnore
    public Integer getOffset() {
        return getSize() * (getIndex() - 1);
    }

    @JsonIgnore
    public Integer getLimit() {
        return getSize();
    }


    public Integer getIndex() {
        if (index == null || index <= 0) {
            return DEF_INDEX;
        }
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getSize() {
        if (size == null || size <= 0) {
            return DEF_SIZE;
        }
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
