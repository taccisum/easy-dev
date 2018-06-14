package cn.tac.framework.easydev.dao.crud;

import cn.tac.framework.easydev.dao.core.CrudMapperSupport;
import cn.tac.framework.easydev.dao.core.RepositorySkeleton;
import cn.tac.framework.easydev.dao.core.bean.RuntimeData4Dao;
import cn.tac.framework.easydev.dao.core.pojo.GenericMinEntity;
import cn.tac.framework.easydev.dao.core.strategy.id.IDGenerator;
import cn.tac.framework.easydev.dao.core.strategy.id.UUIDGenerator;
import cn.tac.framework.easydev.dao.core.util.EntityUtils;
import cn.tac.framework.easydev.dao.crud.config.DaoCrudSupportProperties;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import com.google.common.base.MoreObjects;
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

import javax.persistence.Table;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author tac
 * @since 2.0
 */
@MybatisTest
@RunWith(SpringRunner.class)
@ActiveProfiles({"crud-repository-support", "retrieval4page-repository-support"})
@ImportAutoConfiguration(classes = {MapperAutoConfiguration.class, PageHelperAutoConfiguration.class})
public class Retrieval4PageRepositorySupportTest {
    @Autowired
    private FooMapper4Retrieval4Page mapper;
    @Autowired
    private FooRetrieval4PageRepository repository;
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
    public void select4Page() throws Exception {
        FooEntity4Retrieval4Page criteria = new FooEntity4Retrieval4Page();
        criteria.setBar1("bar1_1");
        assertThat(mapper.selectCount(criteria)).isEqualTo(5);

        PageInfo<FooEntity4Retrieval4Page> info = repository.select4Page(criteria, 1, 3);
        assertThat(info).isNotNull();
        assertThat(info.getList().size()).isEqualTo(3);
        assertThat(info.getTotal()).isEqualTo(5);

        PageInfo<FooEntity4Retrieval4Page> info1 = repository.select4Page(criteria, 2, 3);
        assertThat(info1).isNotNull();
        assertThat(info1.getList().size()).isEqualTo(2);
        assertThat(info1.getTotal()).isEqualTo(5);
    }

    @Test
    public void selectAll4Page() {
        assertThat(mapper.selectCount(null)).isEqualTo(10);
        PageInfo<FooEntity4Retrieval4Page> info = repository.selectAll4Page(1, 6);
        assertThat(info).isNotNull();
        assertThat(info.getList().size()).isEqualTo(6);
        assertThat(info.getTotal()).isEqualTo(10);

        PageInfo<FooEntity4Retrieval4Page> info1 = repository.selectAll4Page(2, 6);
        assertThat(info1).isNotNull();
        assertThat(info1.getList().size()).isEqualTo(4);
        assertThat(info1.getTotal()).isEqualTo(10);
    }

    @Repository
    static class FooRetrieval4PageRepository extends RepositorySkeleton<FooEntity4Retrieval4Page, String>
            implements Retrieval4PageRepositorySupport<FooEntity4Retrieval4Page, String> {
        private DaoCrudSupportProperties daoCrudSupportProperties = new DaoCrudSupportProperties();

        public FooRetrieval4PageRepository(FooMapper4Retrieval4Page mapper) {
            super(mapper, FooEntity4Retrieval4Page.class);
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
                public String tenantId() {
                    return "tac";
                }
            };
        }
    }

    @Mapper
    public interface FooMapper4Retrieval4Page extends CrudMapperSupport<FooEntity4Retrieval4Page> {
    }

    @Table(name = "foo")
    public static class FooEntity4Retrieval4Page extends GenericMinEntity<String> {
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
    }
}
