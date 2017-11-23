package cn.tac.framework.easydev.dao.middle.pojo;

import cn.tac.framework.easydev.dao.core.pojo.GenericMinEntity;
import cn.tac.framework.easydev.dao.core.pojo.InitializingEntity;
import cn.tac.framework.easydev.dao.core.util.EntityUtils;

/**
 * 通用的中间表实体
 *
 * @author tac
 * @since 2.0
 */
public abstract class GenericMiddleEntity<PK, LPK, RPK> extends GenericMinEntity<PK> implements InitializingEntity, RelevanceInfoAware<LPK, RPK> {
    @Override
    public void init() {
        EntityUtils.init(this);
    }
}
