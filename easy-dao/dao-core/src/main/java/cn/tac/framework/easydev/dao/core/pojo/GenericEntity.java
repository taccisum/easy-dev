package cn.tac.framework.easydev.dao.core.pojo;


import cn.tac.framework.easydev.dao.core.strategy.deletedflag.DeletedFlagMapping;
import cn.tac.framework.easydev.dao.core.strategy.deletedflag.IntegerDeletedFlagMapping;
import cn.tac.framework.easydev.dao.core.util.EntityUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import java.util.Date;

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
 */
@Entity
public abstract class GenericEntity<PK> extends GenericMinEntity<PK> implements
        EntityInfoAware<Long>,
        DeletedFlagAware<Integer>,
        InitializingEntity,
        DefaultValue4ParticularFieldsAware {
    public static final String CREATED_BY_FIELD_NAME = "created_by";
    public static final String CREATED_ON_FIELD_NAME = "created_on";
    public static final String UPDATED_BY_FIELD_NAME = "updated_by";
    public static final String UPDATED_ON_FIELD_NAME = "updated_on";
    public static final String DELETED_FLAG_FIELD_NAME = "deleted_flag";

    @Column(name = CREATED_BY_FIELD_NAME)
    @JsonIgnore
    private Long createdBy;

    @OrderBy("desc")
    @Column(name = CREATED_ON_FIELD_NAME)
    @JsonIgnore
    private Date createdOn;

    @Column(name = UPDATED_BY_FIELD_NAME)
    @JsonIgnore
    private Long updatedBy;

    @Column(name = UPDATED_ON_FIELD_NAME)
    @JsonIgnore
    private Date updatedOn;

    @Column(name = DELETED_FLAG_FIELD_NAME)
    @JsonIgnore
    private Integer deletedFlag;

    @Override
    public Long getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public Date getCreatedOn() {
        return createdOn;
    }

    @Override
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public Long getUpdatedBy() {
        return updatedBy;
    }

    @Override
    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public Date getUpdatedOn() {
        return updatedOn;
    }

    @Override
    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Override
    public Integer getDeletedFlag() {
        return deletedFlag;
    }

    @Override
    public void setDeletedFlag(Integer enableFlag) {
        this.deletedFlag = enableFlag;
    }

    @Override
    public void init(){
        EntityUtils.init(this);
    }

    /**
     * 在派生类中可以通过改写该方法来为某些字段赋于默认值
     */
    @Override
    public void initDefaultValue() {
        //do nothing
    }

    @Override
    @JsonIgnore
    public DeletedFlagMapping<Integer> deletedFlagMapping() {
        return IntegerDeletedFlagMapping.instance();
    }
}
