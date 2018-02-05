package cn.tac.framework.easydev.dao.middle;

import cn.tac.framework.easydev.dao.core.CrudMapperSupport;
import cn.tac.framework.easydev.dao.core.strategy.id.IDGenerator;
import cn.tac.framework.easydev.dao.core.strategy.id.UUIDGenerator;
import cn.tac.framework.easydev.dao.middle.pojo.GenericMiddleEntity;
import com.google.common.base.MoreObjects;
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
@ActiveProfiles({"crud-relation-repository-support", "middle-relation-repository-support"})
@ImportAutoConfiguration(MapperAutoConfiguration.class)
public class MiddleRepositorySupportTest {
    @Autowired
    private FooMiddleRepository repository;
    @Autowired
    private FooMapper4Middle mapper;

    @Test
    public void testSimply() {
        assertThat(repository).isNotNull();
        assertThat(mapper).isNotNull();
    }

    @Test
    public void relink() {
        assertThat(mapper.selectAll().size()).isEqualTo(5);
        assertThat(repository.relink("uid_1", 6, 7, 8)).isEqualTo(8);
        assertThat(mapper.selectAll().size()).isEqualTo(3);
    }

    @Repository
    static class FooMiddleRepository extends MiddleRepositorySupport<FooEntity4Middle, String, String, Integer> {
        public FooMiddleRepository(FooMapper4Middle mapper) {
            super(mapper, FooEntity4Middle.class);
        }
    }

    @Mapper
    interface FooMapper4Middle extends CrudMapperSupport<FooEntity4Middle> {
    }

    @Table(name = "foo")
    public static class FooEntity4Middle extends GenericMiddleEntity<String, String, Integer> {
        @Column(name = "user_id")
        private String sourceId;
        @Column(name = "role_id")
        private Integer targetId;

        @Override
        public String getSourceId() {
            return sourceId;
        }

        @Override
        public void setSourceId(String sourceId) {
            this.sourceId = sourceId;
        }

        @Override
        public Integer getTargetId() {
            return targetId;
        }

        @Override
        public void setTargetId(Integer targetId) {
            this.targetId = targetId;
        }

        @Override
        public IDGenerator<String> idGenerator() {
            return UUIDGenerator.instance();
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("id", getId())
                    .add("sourceId", sourceId)
                    .add("targetId", targetId)
                    .toString();
        }
    }
}
