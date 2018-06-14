package cn.tac.framework.easydev.dao.core.util;

import cn.tac.framework.easydev.dao.core.bean.RuntimeData4Dao;
import cn.tac.framework.easydev.dao.core.pojo.GenericBusinessEntity;
import cn.tac.framework.easydev.dao.core.strategy.deletedflag.IntegerDeletedFlagMapping;
import cn.tac.framework.easydev.dao.core.strategy.id.IDGenerator;
import cn.tac.framework.easydev.dao.core.strategy.id.UUIDGenerator;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author tac
 * @since 2.0
 */
public class EntityUtilsTest {
    public static final String ORG_ID = "taccisum";
    public static final String USER_ID = "tac";

    @Before
    public void setUp() throws Exception {
        new EntityUtils().setRuntimeData4Dao(new RuntimeData4Dao() {
            @Override
            public String userId() {
                return USER_ID;
            }

            @Override
            public String tenantId() {
                return ORG_ID;
            }
        });
    }

    @Test
    public void init() throws Exception {
        FooEntity entity = new FooEntity();
        EntityUtils.init(entity);
        assertThat(entity.getId()).isNotBlank();
        assertThat(entity.getCreatedBy()).isEqualTo(USER_ID);
        assertThat(entity.getCreatedOn()).isNotNull();
        assertThat(entity.getUpdatedBy()).isNull();
        assertThat(entity.getUpdatedOn()).isNull();
        assertThat(entity.getDeletedFlag()).isEqualTo(IntegerDeletedFlagMapping.instance().getEnableFlag());
        assertThat(entity.getTenantId()).isEqualTo(ORG_ID);
        assertThat(entity.getBar1()).isEqualTo(FooEntity.BAR1_DEFAULT);
    }

    @Test
    public void initWhenFieldExistValue() throws Exception {
        FooEntity entity = new FooEntity();
        entity.setId("123");
        entity.setCreatedBy("tacc");
        Date now = new Date();
        entity.setCreatedOn(now);
        entity.setUpdatedBy("anit");
        Date now1 = new Date();
        entity.setUpdatedOn(now1);
        entity.setDeletedFlag(IntegerDeletedFlagMapping.instance().getDisableFlag());
        entity.setTenantId("org");
        entity.setBar1("bar111");

        EntityUtils.init(entity);
        assertThat(entity.getId()).isEqualTo("123");
        assertThat(entity.getCreatedBy()).isEqualTo("tacc");
        assertThat(entity.getCreatedOn()).isEqualTo(now);
        assertThat(entity.getUpdatedBy()).isEqualTo("anit");
        assertThat(entity.getUpdatedOn()).isEqualTo(now1);
        assertThat(entity.getDeletedFlag()).isEqualTo(IntegerDeletedFlagMapping.instance().getDisableFlag());
        assertThat(entity.getTenantId()).isEqualTo("org");
        assertThat(entity.getBar1()).isEqualTo(FooEntity.BAR1_DEFAULT);
    }

    @Test
    public void initUpdatingInfo() throws Exception {
        FooEntity entity = new FooEntity();
        EntityUtils.initUpdatingInfo(entity);
        assertThat(entity.getUpdatedBy()).isEqualTo(USER_ID);
        assertThat(entity.getUpdatedOn()).isNotNull();
    }

    @Test
    public void initUpdatingInfoWhenFieldExistValue() throws Exception {
        FooEntity entity = new FooEntity();
        entity.setUpdatedBy("tacc");
        Date date = new Date(1, 1, 1);
        entity.setUpdatedOn(date);
        EntityUtils.initUpdatingInfo(entity);
        assertThat(entity.getUpdatedBy()).isEqualTo(USER_ID);
        assertThat(entity.getUpdatedOn()).isNotEqualTo(date);
    }

    @Test
    public void setBoundary4Query() throws Exception {
        FooEntity entity = new FooEntity();
        EntityUtils.setBoundary4Query(entity);
        assertThat(entity.getTenantId()).isEqualTo(ORG_ID);
    }

    @Test
    public void setBoundary4QueryWhenFieldExistValue() throws Exception {
        FooEntity entity = new FooEntity();
        entity.setTenantId("hello");
        EntityUtils.setBoundary4Query(entity);
        assertThat(entity.getTenantId()).isEqualTo("hello");
    }

    static class FooEntity extends GenericBusinessEntity<String> {
        public static final String BAR1_DEFAULT = "bar1";
        private String bar1;

        public String getBar1() {
            return bar1;
        }

        public void setBar1(String bar1) {
            this.bar1 = bar1;
        }

        @Override
        public void initDefaultValue() {
            bar1 = BAR1_DEFAULT;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("id", getId())
                    .append("createdBy", getCreatedBy())
                    .append("createdOn", getCreatedOn())
                    .append("updatedBy", getUpdatedBy())
                    .append("updatedOn", getUpdatedOn())
                    .append("deletedFlag", getDeletedFlag())
                    .append("tenantId", getTenantId())
                    .append("bar1", bar1)
                    .toString();
        }

        @Override
        public IDGenerator<String> idGenerator() {
            return UUIDGenerator.instance();
        }
    }
}
