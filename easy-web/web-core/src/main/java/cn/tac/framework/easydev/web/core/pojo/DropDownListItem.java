package cn.tac.framework.easydev.web.core.pojo;

/**
 * 定义下拉列表item接口
 *
 * @author tac
 * @since 2.3
 */
public interface DropDownListItem<V> {
    String getName();

    V getValue();
}
