package cn.tac.framework.easydev.dao.core.pojo;

import javax.persistence.Column;

/**
 * 通用的业务实体基类
 *
 * @author tac
 * @since 15/11/2017
 */
public abstract class GenericBusinessEntity<PK> extends GenericEntity<PK> implements BusinessInfoAware {
    public static final String ORG_ID_FIELD_NAME = "organization_id";

    @Column(name = ORG_ID_FIELD_NAME)
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
