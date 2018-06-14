package cn.tac.framework.easydev.dao.core.util;

import cn.tac.framework.easydev.dao.core.bean.RuntimeData4Dao;
import cn.tac.framework.easydev.dao.core.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author tac
 * @since 2.0
 */
public class EntityUtils {
    private static RuntimeData4Dao runtimeData4Dao;

    @Autowired
    public void setRuntimeData4Dao(RuntimeData4Dao runtimeData4Dao) {
        EntityUtils.runtimeData4Dao = runtimeData4Dao;
    }

    public static void init(MinEntityStructureAware entity) {
        initPrimaryKey(entity);
        initEntityInfo(entity);
        if (entity instanceof DeletedFlagAware) {
            if (((DeletedFlagAware) entity).getDeletedFlag() == null) {
                initDeletedFlag(((DeletedFlagAware) entity));
            }
        }
        if (entity instanceof DefaultValue4ParticularFieldsAware) {
            initDefaultValue((DefaultValue4ParticularFieldsAware) entity);
        }
        if (entity instanceof TenantAware) {
            initTenantInfo((TenantAware) entity);
        }
    }

    public static void initUpdatingInfo(MinEntityStructureAware entity) {
        if (entity instanceof UpdatorAwareNew) {
            ((UpdatorAwareNew) entity).setUpdatedBy(runtimeData4Dao == null ? null : runtimeData4Dao.userId());
        }
        if (entity instanceof UpdationTimeAware) {
            ((UpdationTimeAware) entity).setUpdatedOn(new Date());
        }
    }

    public static void setBoundary4Query(MinEntityStructureAware entity) {
        if (entity instanceof TenantAware) {
            if (((TenantAware) entity).getTenantId() == null) {
                setBusinessBoundary4Query((TenantAware) entity);
            }
        }
    }

    private static void initPrimaryKey(MinEntityStructureAware entity) {
        if (entity.getId() == null) {
            entity.setId(entity.idGenerator().generate(null));
        }
    }

    private static void initEntityInfo(MinEntityStructureAware entity) {
        if (entity instanceof CreatorAwareNew) {
            if (((CreatorAwareNew) entity).getCreatedBy() == null) {
                ((CreatorAwareNew) entity).setCreatedBy(runtimeData4Dao == null ? null : runtimeData4Dao.userId());
            }
        }
        if (entity instanceof CreationTimeAware) {
            if (((CreationTimeAware) entity).getCreatedOn() == null) {
                ((CreationTimeAware) entity).setCreatedOn(new Date());
            }
        }
    }

    private static void initDeletedFlag(DeletedFlagAware entity) {
        entity.setDeletedFlag(entity.deletedFlagMapping().getEnableFlag());
    }

    private static void initDefaultValue(DefaultValue4ParticularFieldsAware entity) {
        entity.initDefaultValue();
    }

    private static void initTenantInfo(TenantAware entity) {
        if (entity.getTenantId() == null) {
            entity.setTenantId(runtimeData4Dao == null ? null : runtimeData4Dao.tenantId());
        }
    }

    private static void setBusinessBoundary4Query(TenantAware entity) {
        entity.setTenantId(runtimeData4Dao == null ? null : runtimeData4Dao.tenantId());
    }
}
