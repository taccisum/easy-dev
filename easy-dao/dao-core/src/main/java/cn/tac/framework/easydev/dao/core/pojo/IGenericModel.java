package cn.tac.framework.easydev.dao.core.pojo;

import javax.persistence.Column;
import java.util.Date;

/**
 * 定义通用的model应具有的getter和setter及其它通用方法
 *
 * <br/>
 *
 * 继承此接口后的类应该：
 * <ul>
 *     <li>定义getter/setter相对应的字段，且字段名称必须与getter/setter一致</li>
 *     <li>使用{@link Id}指定一个主键</li>
 *     <li>如有必要，可自行实现更多操作（例如使用{@link Column}手动指定映射到model字段名的表名，详细参考jpa标准）</li>
 * </ul>
 *
 * @author : tac
 * @since : 2017/7/14
 */
public interface IGenericModel<PK> {
    //getter or setter
    PK getId();
    void setId(PK id);
    String getCreatedBy();
    void setCreatedBy(String createdBy);
    Date getCreatedOn();
    void setCreatedOn(Date createdOn);
    String getUpdatedBy();
    void setUpdatedBy(String updatedBy);
    Date getUpdatedOn();
    void setUpdatedOn(Date updatedOn);
    Integer getDeletedFlag();
    void setDeletedFlag(Integer flag);

    /**
     * 用于初始化一个新的实体某些字段的默认值
     */
    void init();
}
