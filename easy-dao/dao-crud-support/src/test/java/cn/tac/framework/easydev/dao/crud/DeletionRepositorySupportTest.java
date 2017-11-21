package cn.tac.framework.easydev.dao.crud;

import cn.tac.framework.easydev.dao.core.CrudMapperSupport;
import cn.tac.framework.easydev.dao.core.RepositorySkeleton;
import cn.tac.framework.easydev.dao.core.pojo.DeletedFlagAware;
import cn.tac.framework.easydev.dao.core.pojo.GenericEntity;
import cn.tac.framework.easydev.dao.core.pojo.GenericMinEntity;
import cn.tac.framework.easydev.dao.core.pojo.MinEntityStructureAware;
import cn.tac.framework.easydev.dao.core.strategy.deletedflag.DeletedFlagMapping;
import cn.tac.framework.easydev.dao.core.strategy.deletedflag.IntegerDeletedFlagMapping;
import cn.tac.framework.easydev.dao.core.strategy.id.IDGenerator;
import cn.tac.framework.easydev.dao.core.strategy.id.UUIDGenerator;
import cn.tac.framework.easydev.dao.crud.config.DaoCrudSupportProperties;
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

import javax.persistence.Column;
import javax.persistence.Table;

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
        repository.setDaoCrudSupportProperties(true, true);
        assertThat(repository.deleteByPrimaryKey("1")).isEqualTo(1);
        FooEntity4Deletion o = mapper.selectByPrimaryKey("1");
        assertThat(o).isNotNull();
        assertThat(o.getDeletedFlag()).isEqualTo(o.getDeletedFlagMapping().getDisableFlag());

        repository.setDaoCrudSupportProperties(false, true);
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

        public void setDaoCrudSupportProperties(boolean logicDeleted, boolean containDeleted) {
            DaoCrudSupportProperties p = new DaoCrudSupportProperties();
            p.setContainDeleted(containDeleted);
            p.setLogicDeleted(logicDeleted);
            daoCrudSupportProperties = p;
        }

        @Override
        public DaoCrudSupportProperties getDaoCrudSupportProperties() {
            return daoCrudSupportProperties;
        }
    }

    @Mapper
    interface FooMapper4Deletion extends CrudMapperSupport<FooEntity4Deletion> {
    }

    @Table(name = "foo")
    public static class FooEntity4Deletion extends GenericMinEntity<String> implements DeletedFlagAware<Integer> {
        private String bar1;
        @Column(name = GenericEntity.DELETED_FLAG_FIELD_NAME)
        private Integer deletedFlag;

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
        public IDGenerator<String> getIDGenerator() {
            return UUIDGenerator.instance();
        }

        @Override
        public DeletedFlagMapping<Integer> getDeletedFlagMapping() {
            return IntegerDeletedFlagMapping.instance();
        }
    }
}
