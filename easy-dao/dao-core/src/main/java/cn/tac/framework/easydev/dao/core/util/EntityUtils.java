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
            initDeletedFlag(((DeletedFlagAware) entity));
        }
        if (entity instanceof DefaultValue4ParticularFieldsAware) {
            initDefaultValue((DefaultValue4ParticularFieldsAware) entity);
        }
        if (entity instanceof BusinessInfoAware) {
            initBusinessInfo((BusinessInfoAware) entity);
        }
    }

    public static void initUpdatingInfo(MinEntityStructureAware entity) {
        if (entity instanceof UpdatorAware) {
            ((UpdatorAware) entity).setUpdatedBy(runtimeData4Dao == null ? null : runtimeData4Dao.userId());
        }
        if (entity instanceof UpdationTimeAware) {
            ((UpdationTimeAware) entity).setUpdatedOn(new Date());
        }
    }

    public static void setBoundary4Query(MinEntityStructureAware entity) {
        if (entity instanceof BusinessInfoAware) {
            setBusinessBoundary4Query((BusinessInfoAware) entity);
        }
    }

    private static void initPrimaryKey(MinEntityStructureAware entity) {
        entity.setId(entity.getIDGenerator().generate(null));
    }

    private static void initEntityInfo(MinEntityStructureAware entity) {
        if (entity instanceof CreatorAware) {
            ((CreatorAware) entity).setCreatedBy(runtimeData4Dao == null ? null : runtimeData4Dao.userId());
        }
        if (entity instanceof CreationTimeAware) {
            ((CreationTimeAware) entity).setCreatedOn(new Date());
        }
        if (entity instanceof UpdatorAware) {
            ((UpdatorAware) entity).setUpdatedBy(null);
        }
        if (entity instanceof UpdationTimeAware) {
            ((UpdationTimeAware) entity).setUpdatedOn(null);
        }
    }

    private static void initDeletedFlag(DeletedFlagAware entity) {
        entity.setDeletedFlag(entity.getDeletedFlagMapping().getEnableFlag());
    }

    private static void initDefaultValue(DefaultValue4ParticularFieldsAware entity) {
        entity.initDefaultValue();
    }

    private static void initBusinessInfo(BusinessInfoAware entity) {
        entity.setOrganizationId(runtimeData4Dao == null ? null : runtimeData4Dao.organizationId());
    }

    private static void setBusinessBoundary4Query(BusinessInfoAware entity) {
        entity.setOrganizationId(runtimeData4Dao == null ? null : runtimeData4Dao.organizationId());
    }
}
