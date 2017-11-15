package cn.tac.framework.easydev.dao.core.pojo;


import cn.tac.framework.easydev.core.util.SpringUtils;
import cn.tac.framework.easydev.dao.core.bean.RuntimeData4Dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OrderBy;
import java.io.Serializable;
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
 * @author : tac
 * @since : 2017/11/1
 */
@Entity
public abstract class GenericEntity<PK> implements PrimaryKeyAware<PK>, EntityInfoAware, DeletedFlagAware, InitializingEntity, Serializable {
    public static final String ID_FIELD_NAME = "id";
    public static final String CREATED_BY_FIELD_NAME = "created_by";
    public static final String CREATED_ON_FIELD_NAME = "created_on";
    public static final String UPDATED_BY_FIELD_NAME = "updated_by";
    public static final String UPDATED_ON_FIELD_NAME = "updated_on";
    public static final String DELETED_FLAG_FIELD_NAME = "deleted_flag";

    @Id
    @Column(name = ID_FIELD_NAME)
    private PK id;
    @Column(name = CREATED_BY_FIELD_NAME)
    private String createdBy;
    @OrderBy("desc")
    @Column(name = CREATED_ON_FIELD_NAME)
    private Date createdOn;
    @Column(name = UPDATED_BY_FIELD_NAME)
    private String updatedBy;
    @Column(name = UPDATED_ON_FIELD_NAME)
    private Date updatedOn;
    @Column(name = DELETED_FLAG_FIELD_NAME)
    private Integer deletedFlag;

    @Override
    public PK getId() {
        return id;
    }

    @Override
    public void setId(PK id) {
        this.id = id;
    }

    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
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
    public String getUpdatedBy() {
        return updatedBy;
    }

    @Override
    public void setUpdatedBy(String updatedBy) {
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

    /**
     * {@inheritDoc}
     *
     * @see #newId()
     * @see #setDef()
     */
    @Override
    public void init(){
        setId(newId());
        setCreatedBy(SpringUtils.getBean(RuntimeData4Dao.class).userId());
        setCreatedOn(new Date());
        setUpdatedBy(null);
        setUpdatedOn(null);
        setDeletedFlag(0);

        setDef();
    }

    /**
     * 在派生类中可以通过改写该方法来为某些字段赋于默认值
     */
    protected void setDef(){
    }

    protected abstract PK newId();
}
