package cn.tac.framework.easydev.dao.crud;

import cn.tac.framework.easydev.dao.core.CrudMapperSupport;
import cn.tac.framework.easydev.dao.core.RepositorySkeleton;
import cn.tac.framework.easydev.dao.core.bean.RuntimeData4Dao;
import cn.tac.framework.easydev.dao.core.pojo.*;
import cn.tac.framework.easydev.dao.core.strategy.deletedflag.DeletedFlagMapping;
import cn.tac.framework.easydev.dao.core.strategy.deletedflag.IntegerDeletedFlagMapping;
import cn.tac.framework.easydev.dao.core.strategy.id.IDGenerator;
import cn.tac.framework.easydev.dao.core.strategy.id.UUIDGenerator;
import cn.tac.framework.easydev.dao.core.util.EntityUtils;
import cn.tac.framework.easydev.dao.crud.config.DaoCrudSupportProperties;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.ibatis.annotations.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.autoconfigure.MapperAutoConfiguration;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author tac
 * @since 2.0
 */
@MybatisTest
@RunWith(SpringRunner.class)
@ActiveProfiles({"crud-repository-support", "updating-repository-support"})
@ImportAutoConfiguration(MapperAutoConfiguration.class)
public class UpdatingRepositorySupportTest {
    @Autowired
    private FooMapper4Updating mapper;
    @Autowired
    private FooUpdatingRepository repository;
    @Autowired
    private RuntimeData4Dao runtimeData4Dao;

    @Test
    public void testSimply() {
        assertThat(mapper).isNotNull();
        assertThat(repository).isNotNull();
        assertThat(runtimeData4Dao).isNotNull();
        mapper.selectAll();
    }

    @Test
    public void updateByPrimaryKeySelectiveWhenIdIsNull() {
        FooEntity4Updating o = new FooEntity4Updating();
        o.setId(null);
        assertThat(repository.updateByPrimaryKeySelective(o)).isEqualTo(0);
    }

    @Test
    public void updateByPrimaryKeySelective() {
        FooEntity4Updating pre = mapper.selectByPrimaryKey("1");
        assertThat(pre).isNotNull();
        assertThat(pre.getBar1()).isEqualTo("bar1_1");
        assertThat(pre.getBar2()).isEqualTo("bar2_1");
        assertThat(pre.getUpdatedBy()).isNull();
        assertThat(pre.getUpdatedOn()).isNull();

        FooEntity4Updating o = new FooEntity4Updating();
        o.setId("1");
        o.setBar1("hhhh");
        o.setBar2(null);
        assertThat(repository.updateByPrimaryKeySelective(o)).isEqualTo(1);

        FooEntity4Updating after = mapper.selectByPrimaryKey("1");
        assertThat(after).isNotNull();
        assertThat(after.getBar1()).isEqualTo("hhhh");
        assertThat(after.getBar2()).isEqualTo("bar2_1");
        assertThat(after.getUpdatedBy()).isEqualTo("tac");
        assertThat(after.getUpdatedOn()).isNotNull();
    }

    @Repository
    static class FooUpdatingRepository extends RepositorySkeleton<FooEntity4Updating, String>
            implements UpdatingRepositorySupport<FooEntity4Updating, String> {
        public FooUpdatingRepository(FooMapper4Updating mapper) {
            super(mapper, FooEntity4Updating.class);
        }

        @Override
        public DaoCrudSupportProperties getDaoCrudSupportProperties() {
            return new DaoCrudSupportProperties();
        }
    }


    @TestConfiguration
    static class Configuration {
        @Bean
        public EntityUtils entityUtils(RuntimeData4Dao runtimeData4Dao) {
            EntityUtils bean = new EntityUtils();
            bean.setRuntimeData4Dao(runtimeData4Dao);
            return bean;
        }

        @Bean
        public RuntimeData4Dao runtimeData4Dao() {
            return new RuntimeData4Dao() {
                @Override
                public String userId() {
                    return "tac";
                }

                @Override
                public String organizationId() {
                    return "tac's org";
                }
            };
        }
    }

    @Mapper
    public interface FooMapper4Updating extends CrudMapperSupport<FooEntity4Updating> {
    }

    @Table(name = "foo")
    public static class FooEntity4Updating extends GenericMinEntity<String> implements EntityInfoAware, DeletedFlagAware<Integer> {
        private String bar1;
        private String bar2;

        @Column(name = GenericEntity.CREATED_BY_FIELD_NAME)
        private String createdBy;

        @Column(name = GenericEntity.CREATED_ON_FIELD_NAME)
        private Date createdOn;

        @Column(name = GenericEntity.UPDATED_BY_FIELD_NAME)
        private String updatedBy;

        @Column(name = GenericEntity.UPDATED_ON_FIELD_NAME)
        private Date updatedOn;

        @Column(name = GenericEntity.DELETED_FLAG_FIELD_NAME)
        private Integer deletedFlag;

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
        public String getCreatedBy() {
            return createdBy;
        }

        @Override
        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        @Override
        public Date getCreatedOn() {
            return createdOn;
        }

        @Override
        public void setCreatedOn(Date createdOn) {
            this.createdOn = createdOn;
        }

        @Override
        public String getUpdatedBy() {
            return updatedBy;
        }

        @Override
        public void setUpdatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
        }

        @Override
        public Date getUpdatedOn() {
            return updatedOn;
        }

        @Override
        public void setUpdatedOn(Date updatedOn) {
            this.updatedOn = updatedOn;
        }

        @Override
        public Integer getDeletedFlag() {
            return deletedFlag;
        }

        @Override
        public void setDeletedFlag(Integer deletedFlag) {
            this.deletedFlag = deletedFlag;
        }

        @Override
        public IDGenerator<String> getIDGenerator() {
            return UUIDGenerator.instance();
        }

        @Override
        public DeletedFlagMapping<Integer> getDeletedFlagMapping() {
            return IntegerDeletedFlagMapping.instance();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("id", getId())
                    .append("bar1", bar1)
                    .append("createdBy", createdBy)
                    .append("createdOn", createdOn)
                    .append("updatedBy", updatedBy)
                    .append("updatedOn", updatedOn)
                    .append("deletedFlag", deletedFlag)
                    .toString();
        }
    }
}
