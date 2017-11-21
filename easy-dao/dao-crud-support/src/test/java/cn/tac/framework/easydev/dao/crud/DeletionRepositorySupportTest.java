package cn.tac.framework.easydev.dao.crud;

import cn.tac.framework.easydev.dao.core.RepositorySkeleton;
import cn.tac.framework.easydev.dao.crud.config.DaoCrudSupportProperties;
import cn.tac.framework.easydev.dao.crud.mapper.FooMapper4Creation;
import cn.tac.framework.easydev.dao.crud.mapper.FooMapper4Deletion;
import cn.tac.framework.easydev.dao.crud.pojo.FooEntity4Creation;
import cn.tac.framework.easydev.dao.crud.pojo.FooEntity4Deletion;
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
@ActiveProfiles({"crud-repository-support", "deletion-repository-support"})
@ImportAutoConfiguration(MapperAutoConfiguration.class)
public class DeletionRepositorySupportTest {
    @Autowired
    private FooMapper4Deletion mapper;
    @Autowired
    private FooDeletionRepository repository;

    @Test
    public void testSimply() {
        assertThat(mapper).isNotNull();
        assertThat(repository).isNotNull();
    }

    @Test
    public void deleteByPrimaryKey() {
        repository.setDaoCrudSupportProperties(true);
        assertThat(repository.deleteByPrimaryKey("1")).isEqualTo(1);
        FooEntity4Deletion o = mapper.selectByPrimaryKey("1");

        repository.setDaoCrudSupportProperties(false);
        assertThat(repository.deleteByPrimaryKey("2")).isEqualTo(1);
        FooEntity4Deletion o1 = mapper.selectByPrimaryKey("2");
        assertThat(o1).isNull();
    }

    @Repository
    static class FooDeletionRepository extends RepositorySkeleton<FooEntity4Deletion, String>
            implements DeletionRepositorySupport<FooEntity4Deletion, String> {
        private DaoCrudSupportProperties daoCrudSupportProperties = new DaoCrudSupportProperties();

        public FooDeletionRepository(FooMapper4Deletion mapper) {
            super(mapper, FooEntity4Deletion.class);
        }

        public void setDaoCrudSupportProperties(boolean logicDeleted) {
            DaoCrudSupportProperties p = new DaoCrudSupportProperties();
            p.setLogicDeleted(logicDeleted);
            daoCrudSupportProperties = p;
        }

        @Override
        public DaoCrudSupportProperties getDaoCrudSupportProperties() {
            return daoCrudSupportProperties;
        }
    }
}
