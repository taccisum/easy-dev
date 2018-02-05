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
import org.junit.Before;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author tac
 * @since 2.0
 */
@MybatisTest
@RunWith(SpringRunner.class)
@ActiveProfiles({"crud-repository-support", "retrieval-repository-support"})
@ImportAutoConfiguration(MapperAutoConfiguration.class)
public class RetrievalRepositorySupportTest {
    @Autowired
    private FooMapper4Retrieval mapper;
    @Autowired
    private FooRetrievalRepository repository;
    @Autowired
    private RuntimeData4Dao runtimeData4Dao;

    @Before
    public void setUp() throws Exception {
        repository.setDaoCrudSupportProperties(new DaoCrudSupportProperties());     //默认配置
    }

    @Test
    public void testSimply() {
        assertThat(mapper).isNotNull();
        assertThat(repository).isNotNull();
        assertThat(runtimeData4Dao).isNotNull();
    }

    @Test
    public void select() {
        FooEntity4Retrieval o = new FooEntity4Retrieval();
        o.setBar1("bar1_1");
        List<FooEntity4Retrieval> ls = repository.select(o);
        assertThat(ls).isNotEmpty();
        assertThat(ls.size()).isEqualTo(1);
    }

    @Test
    public void selectByPrimaryKey() {
        repository.setDaoCrudSupportProperties(true, false);
        FooEntity4Retrieval o = repository.selectByPrimaryKey("1");
        assertThat(o).isNotNull();
        assertThat(o.getBar1()).isEqualTo("bar1_1");
        FooEntity4Retrieval o1 = repository.selectByPrimaryKey("3");
        assertThat(o1).isNull();
        FooEntity4Retrieval o2 = repository.selectByPrimaryKey("4");
        assertThat(o2).isNull();

        repository.setDaoCrudSupportProperties(true, true);
        FooEntity4Retrieval o3 = repository.selectByPrimaryKey("3");
        assertThat(o3).isNotNull();
        assertThat(o3.getBar1()).isEqualTo("bar1_3");
        assertThat(o3.getDeletedFlag()).isEqualTo(o3.deletedFlagMapping().getDisableFlag());

        repository.setDaoCrudSupportProperties(false, false);
        FooEntity4Retrieval o4 = repository.selectByPrimaryKey("4");
        assertThat(o4).isNotNull();
        assertThat(o4.getBar1()).isEqualTo("bar1_4");
        assertThat(o4.getOrganizationId()).isEqualTo("anit");
    }

    @Test
    public void selectAll() {
        List<FooEntity4Retrieval> ls = repository.selectAll();
        assertThat(ls).isNotEmpty();
        assertThat(ls.size()).isEqualTo(2);
    }

    @Repository
    static class FooRetrievalRepository extends RepositorySkeleton<FooEntity4Retrieval, String>
            implements RetrievalRepositorySupport<FooEntity4Retrieval, String> {
        private DaoCrudSupportProperties daoCrudSupportProperties = new DaoCrudSupportProperties();

        public FooRetrievalRepository(FooMapper4Retrieval mapper) {
            super(mapper, FooEntity4Retrieval.class);
        }

        public void setDaoCrudSupportProperties(boolean boundary, boolean containDeleted) {
            DaoCrudSupportProperties p = new DaoCrudSupportProperties();
            p.setBoundary(boundary);
            p.setContainDeleted(containDeleted);
            daoCrudSupportProperties = p;
        }

        public void setDaoCrudSupportProperties(DaoCrudSupportProperties daoCrudSupportProperties) {
            this.daoCrudSupportProperties = daoCrudSupportProperties;
        }

        @Override
        public DaoCrudSupportProperties getDaoCrudSupportProperties() {
            return daoCrudSupportProperties;
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
                    return null;
                }

                @Override
                public String organizationId() {
                    return "tac";
                }
            };
        }
    }

    @Mapper
    public interface FooMapper4Retrieval extends CrudMapperSupport<FooEntity4Retrieval> {
    }

    @Table(name = "foo")
    public static class FooEntity4Retrieval extends GenericMinEntity<String> implements DeletedFlagAware<Integer>, BusinessInfoAware {
        private String bar1;

        @Column(name = GenericEntity.DELETED_FLAG_FIELD_NAME)
        private Integer deletedFlag;

        @Column(name = GenericBusinessEntity.ORGANIZATION_ID_FIELD_NAME)
        private String organizationId;

        public String getBar1() {
            return bar1;
        }

        public void setBar1(String bar1) {
            this.bar1 = bar1;
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
        public String getOrganizationId() {
            return organizationId;
        }

        @Override
        public void setOrganizationId(String organizationId) {
            this.organizationId = organizationId;
        }

        @Override
        public IDGenerator<String> idGenerator() {
            return UUIDGenerator.instance();
        }

        @Override
        public DeletedFlagMapping<Integer> deletedFlagMapping() {
            return IntegerDeletedFlagMapping.instance();
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .append("id", getId())
                    .append("bar1", bar1)
                    .append("deletedFlag", deletedFlag)
                    .append("organizationId", organizationId)
                    .toString();
        }
    }
}
