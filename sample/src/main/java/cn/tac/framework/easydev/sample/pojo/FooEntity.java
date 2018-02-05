package cn.tac.framework.easydev.sample.pojo;

import cn.tac.framework.easydev.dao.core.pojo.GenericMinEntity;
import cn.tac.framework.easydev.dao.core.pojo.InitializingEntity;
import cn.tac.framework.easydev.dao.core.strategy.id.IDGenerator;
import cn.tac.framework.easydev.dao.core.strategy.id.UUIDGenerator;
import cn.tac.framework.easydev.dao.core.util.EntityUtils;
import com.google.common.base.MoreObjects;

import javax.persistence.Table;

/**
 * @author tac
 * @since 03/12/2017
 */
@Table(name = "foo")
public class FooEntity extends GenericMinEntity<String> implements InitializingEntity {
    private String bar1;

    public String getBar1() {
        return bar1;
    }

    public void setBar1(String bar1) {
        this.bar1 = bar1;
    }

    @Override
    public IDGenerator<String> idGenerator() {
        return UUIDGenerator.instance();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", getId())
                .add("bar1", bar1)
                .toString();
    }

    @Override
    public void init() {
        EntityUtils.init(this);
    }
}
