package cn.tac.framework.easydev.dao.crud;

import cn.tac.framework.easydev.dao.core.CrudMapperSupport;
import cn.tac.framework.easydev.dao.core.bean.RuntimeData4Dao;
import cn.tac.framework.easydev.dao.crud.mapper.FooMapper4Creation;
import cn.tac.framework.easydev.dao.crud.pojo.FooEntity4Creation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.autoconfigure.MapperAutoConfiguration;

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
}
