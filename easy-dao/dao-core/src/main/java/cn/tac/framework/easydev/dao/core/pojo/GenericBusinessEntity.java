package cn.tac.framework.easydev.dao.core.pojo;

import javax.persistence.Column;

/**
 * 通用的业务实体基类
 *
 * @author tac
 * @since 2.0
 */
public abstract class GenericBusinessEntity<PK> extends GenericEntity<PK> implements BusinessInfoAware {
    public static final String ORGANIZATION_ID_FIELD_NAME = "organization_id";

    @Column(name = ORGANIZATION_ID_FIELD_NAME)
    private String organizationId;

    @Override
    public String getOrganizationId() {
        return organizationId;
    }

    @Override
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }
}
