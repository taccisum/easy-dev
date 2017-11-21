package cn.tac.framework.easydev.dao.crud;

import cn.tac.framework.easydev.dao.core.CrudMapperSupport;
import cn.tac.framework.easydev.dao.core.pojo.GenericMinEntity;
import cn.tac.framework.easydev.dao.core.pojo.InitializingEntity;
import cn.tac.framework.easydev.dao.core.strategy.id.IDGenerator;
import cn.tac.framework.easydev.dao.core.strategy.id.UUIDGenerator;
import cn.tac.framework.easydev.dao.core.util.EntityUtils;
import org.apache.ibatis.annotations.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
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
@ActiveProfiles({"crud-repository-support", "creation-repository-support"})
@ImportAutoConfiguration(MapperAutoConfiguration.class)
public class CreationRepositorySupportTest {
    @Autowired
    private FooMapper4Creation mapper;
    @Autowired
    private FooCreationRepository repository;

    @Test
    public void testSimply() {
        assertThat(mapper).isNotNull();
        assertThat(repository).isNotNull();
    }

    @Test
    public void insert() throws Exception {
        FooEntity4Creation o = new FooEntity4Creation();
        assertThat(repository.insert(o)).isEqualTo(1);
        FooEntity4Creation entity = mapper.selectByPrimaryKey(o.getId());
        assertThat(entity).isNotNull();
        assertThat(entity.getBar1()).isEqualTo("bar1");
    }

    @Test
    public void insertSelective() throws Exception {
        FooEntity4Creation o = new FooEntity4Creation();
        assertThat(repository.insertSelective(o)).isEqualTo(1);
        FooEntity4Creation entity = mapper.selectByPrimaryKey(o.getId());
        assertThat(entity).isNotNull();
        assertThat(entity.getBar2()).isEqualTo("bar2_default");
    }

    @Repository
    static class FooCreationRepository implements CreationRepositorySupport<FooEntity4Creation, String> {
        @Autowired
        private FooMapper4Creation mapper;

        @Override
        public CrudMapperSupport<FooEntity4Creation> getMapper() {
            return mapper;
        }
    }

    @Mapper
    interface FooMapper4Creation extends CrudMapperSupport<FooEntity4Creation> {
    }

    @Table(name = "foo")
    static class FooEntity4Creation extends GenericMinEntity<String> implements InitializingEntity {
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

}
