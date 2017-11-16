package cn.tac.framework.easydev.dao.core.util;

import cn.tac.framework.easydev.dao.core.bean.RuntimeData4Dao;
import cn.tac.framework.easydev.dao.core.pojo.*;

import java.util.Date;

/**
 * @author tac
 * @since 15/11/2017
 */
public class EntityUtils {
    private static RuntimeData4Dao runtimeData4Dao;

    public void setRuntimeData4Dao(RuntimeData4Dao runtimeData4Dao) {
        EntityUtils.runtimeData4Dao = runtimeData4Dao;
    }

    public static void init(MinEntityStructure entity) {
        initPrimaryKey(entity);
        if (entity instanceof EntityInfoAware) {
            initEntityInfo((EntityInfoAware) entity);
        }
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

    public static void initUpdatingInfo(EntityInfoAware entity) {
        entity.setUpdatedOn(new Date());
        entity.setUpdatedBy(runtimeData4Dao == null ? null : runtimeData4Dao.userId());
    }

    public static void setBoundary4Query(MinEntityStructure entity) {
        if (entity instanceof BusinessInfoAware) {
            setBusinessBoundary4Query((BusinessInfoAware) entity);
        }
    }

    private static void initPrimaryKey(MinEntityStructure entity) {
        entity.setId(entity.getIDGenerator().generate(null));
    }

    private static void initEntityInfo(EntityInfoAware entity) {
        entity.setCreatedBy(runtimeData4Dao == null ? null : runtimeData4Dao.userId());
        entity.setCreatedOn(new Date());
        entity.setUpdatedBy(null);
        entity.setUpdatedOn(null);
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