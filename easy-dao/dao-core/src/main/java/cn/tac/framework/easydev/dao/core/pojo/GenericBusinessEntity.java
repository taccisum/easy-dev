package cn.tac.framework.easydev.dao.core.pojo;

import javax.persistence.Column;

/**
 * 通用的业务实体基类
 *
 * @author tac
 * @since 2.0
 */
@Deprecated
public abstract class GenericBusinessEntity<PK> extends GenericEntity<PK> implements TenantAware<String> {
    public static final String TENANT_ID_FIELD_NAME = "tenant_id";

    @Column(name = TENANT_ID_FIELD_NAME)
    private String tenantId;

    @Override
    public String getTenantId() {
        return tenantId;
    }

    @Override
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
