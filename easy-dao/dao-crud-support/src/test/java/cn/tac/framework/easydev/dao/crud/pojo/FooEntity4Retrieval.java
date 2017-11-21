package cn.tac.framework.easydev.dao.crud.pojo;

import cn.tac.framework.easydev.dao.core.pojo.*;
import cn.tac.framework.easydev.dao.core.strategy.deletedflag.DeletedFlagMapping;
import cn.tac.framework.easydev.dao.core.strategy.deletedflag.IntegerDeletedFlagMapping;
import cn.tac.framework.easydev.dao.core.strategy.id.IDGenerator;
import cn.tac.framework.easydev.dao.core.strategy.id.UUIDGenerator;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * @author tac
 * @since 2.0
 */
@Table(name = "foo")
public class FooEntity4Retrieval extends MinEntityStructure<String>  implements DeletedFlagAware<Integer>, BusinessInfoAware {
    private String bar1;

    @Column(name = GenericEntity.DELETED_FLAG_FIELD_NAME)
    private Integer deletedFlag;

    @Column(name = GenericBusinessEntity.ORGANIZATION_ID_FIELD_NAME)
    private String organizationId;

    public String getBar1() {
        return bar1;
    }

    public void setBar1(String bar1) {
        this.bar1 = bar1;
    }

    @Override
    public Integer getDeletedFlag() {
        return deletedFlag;
    }

    @Override
    public void setDeletedFlag(Integer deletedFlag) {
        this.deletedFlag = deletedFlag;
    }

    @Override
    public String getOrganizationId() {
        return organizationId;
    }

    @Override
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    @Override
    public IDGenerator<String> getIDGenerator() {
        return UUIDGenerator.instance();
    }

    @Override
    public DeletedFlagMapping<Integer> getDeletedFlagMapping() {
        return IntegerDeletedFlagMapping.instance();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", getId())
                .append("bar1", bar1)
                .append("deletedFlag", deletedFlag)
                .append("organizationId", organizationId)
                .toString();
    }
}
