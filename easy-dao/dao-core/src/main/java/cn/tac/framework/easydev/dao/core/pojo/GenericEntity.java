package cn.tac.framework.easydev.dao.core.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 通用实体模型基类
 *
 * <p>
 *     由于使用了泛型主键，在运行时id的类型将被擦除为{@link Object}，因此使用{@link cn.tac.framework.easydev.core.util.BeanUtilsWrapper}
 *     将实体类的值复制到dto对象时将无法正常复制id，解决方案有二：
 *     <ul>
 *         <li>在实际代码中手动赋值：{@code dto.setId(entity.getId())}</li>
 *         <li>将dto中的id类型更改为{@link Object}</li>
 *     </ul>
 * </p>
 *
 * <p>
 *     实际应用中，也可以继承{@link MinEntityStructureAware}（保证实体的最小结构），通过实现所需接口的形式来构建属于你自己的实体模型基类
 * </p>
 *
 * @author tac
 * @since 2.0
 * @deprecated 推荐使用GenericEntityNew
 * @see GenericEntityNew
 */
@Entity
public abstract class GenericEntity<PK> extends GenericEntityNew<PK, String> {
    public static final String CREATED_BY_FIELD_NAME = GenericEntityNew.CREATED_BY_FIELD_NAME;
    public static final String UPDATED_BY_FIELD_NAME = GenericEntityNew.UPDATED_BY_FIELD_NAME;

    @Column(name = CREATED_BY_FIELD_NAME)
    @JsonIgnore
    private String createdBy;

    @Column(name = UPDATED_BY_FIELD_NAME)
    @JsonIgnore
    private String updatedBy;

    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String getUpdatedBy() {
        return updatedBy;
    }

    @Override
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
