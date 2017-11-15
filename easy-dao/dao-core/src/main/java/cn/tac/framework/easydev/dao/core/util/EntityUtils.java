package cn.tac.framework.easydev.dao.core.util;

import cn.tac.framework.easydev.core.util.SpringUtils;
import cn.tac.framework.easydev.dao.core.bean.RuntimeData4Dao;
import cn.tac.framework.easydev.dao.core.pojo.DefaultValue4ParticularFieldsAware;
import cn.tac.framework.easydev.dao.core.pojo.DeletedFlagAware;
import cn.tac.framework.easydev.dao.core.pojo.EntityInfoAware;
import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructure;

import java.util.Date;

/**
 * @author tac
 * @since 15/11/2017
 */
public abstract class EntityUtils {
    public static void init(MinEntityStructure entity){
        initPrimaryKey(entity);
        if(entity instanceof EntityInfoAware){
            initEntityInfo((EntityInfoAware) entity);
        }
        if(entity instanceof DeletedFlagAware){
            initDeletedFlag(((DeletedFlagAware) entity));
        }
        if(entity instanceof DefaultValue4ParticularFieldsAware){
            initDefaultValue((DefaultValue4ParticularFieldsAware) entity);
        }
    }

    private static void initPrimaryKey(MinEntityStructure entity) {
        entity.setId(entity.getIDGenerator().generate(null));
    }

    private static void initEntityInfo(EntityInfoAware entity) {
        entity.setCreatedBy(SpringUtils.getBean(RuntimeData4Dao.class).userId());
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
}
