package cn.tac.framework.easydev.dao.crud.pojo;

import cn.tac.framework.easydev.dao.core.pojo.InitializingEntity;
import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructure;
import cn.tac.framework.easydev.dao.core.strategy.id.IDGenerator;
import cn.tac.framework.easydev.dao.core.strategy.id.UUIDGenerator;
import cn.tac.framework.easydev.dao.core.util.EntityUtils;

import javax.persistence.Table;

/**
 * @author tac
 * @since 2.0
 */
@Table(name = "foo")
public class FooEntity4Creation extends MinEntityStructure<String> implements InitializingEntity {
    private String bar1;
    private String bar2;

    public String getBar1() {
        return bar1;
    }

    public void setBar1(String bar1) {
        this.bar1 = bar1;
    }

    public String getBar2() {
        return bar2;
    }

    public void setBar2(String bar2) {
        this.bar2 = bar2;
    }

    @Override
    public IDGenerator<String> getIDGenerator() {
        return UUIDGenerator.instance();
    }

    @Override
    public void init() {
        EntityUtils.init(this);
        if (getBar1() == null) {
            setBar1("bar1");
        }
    }
}


