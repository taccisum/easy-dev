package cn.tac.framework.easydev.dao.middle;

import cn.tac.framework.easydev.dao.core.CrudMapperSupport;
import cn.tac.framework.easydev.dao.core.RepositorySkeleton;
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
@ActiveProfiles({"crud-relation-repository-support", "delete-relation-repository-support"})
@ImportAutoConfiguration(MapperAutoConfiguration.class)
public class DeleteRelationRepositorySupportTest {
    @Autowired
    private FooDeleteRelationRepository repository;
    @Autowired
    private FooMapper4DeleteRelation mapper;

    @Test
    public void testSimply() {
        assertThat(repository).isNotNull();
        assertThat(mapper).isNotNull();
        assertThat(mapper.selectAll().size()).isEqualTo(10);
    }

    @Test
    public void unlink() {
        assertThat(repository.unlink("uid_1")).isEqualTo(0);
        assertThat(repository.unlink("uid_1", 1, 2, 4)).isEqualTo(2);
        assertThat(mapper.selectAll().size()).isEqualTo(8);
        assertThat(repository.unlink("uid_2", 1, 2, 4)).isEqualTo(1);
        assertThat(mapper.selectAll().size()).isEqualTo(7);
    }

    @Test
    public void unlinkInversely() {
        assertThat(repository.unlinkInversely(6)).isEqualTo(0);
        assertThat(repository.unlinkInversely(6, "uid_3", "uid_4", "uid_6")).isEqualTo(2);
        assertThat(mapper.selectAll().size()).isEqualTo(8);
        assertThat(repository.unlinkInversely(7, "uid_3", "uid_4", "uid_6")).isEqualTo(1);
        assertThat(mapper.selectAll().size()).isEqualTo(7);
    }

    @Test
    public void unlinkAll() {
        assertThat(repository.unlinkAll("uid_1")).isEqualTo(3);
        assertThat(mapper.selectAll().size()).isEqualTo(7);
        assertThat(repository.unlinkAll("uid_2")).isEqualTo(2);
        assertThat(mapper.selectAll().size()).isEqualTo(5);
    }

    @Test
    public void unlinkAllInversely() {
        assertThat(repository.unlinkAllInversely(6)).isEqualTo(3);
        assertThat(mapper.selectAll().size()).isEqualTo(7);
        assertThat(repository.unlinkAllInversely(7)).isEqualTo(2);
        assertThat(mapper.selectAll().size()).isEqualTo(5);
    }

    @Repository
    static class FooDeleteRelationRepository extends RepositorySkeleton<FooEntity4DeleteRelation, String>
            implements DeleteRelationRepositorySupport<FooEntity4DeleteRelation, String, String, Integer> {

        public FooDeleteRelationRepository(FooMapper4DeleteRelation mapper) {
            super(mapper, FooEntity4DeleteRelation.class);
        }
    }

    @Mapper
    interface FooMapper4DeleteRelation extends CrudMapperSupport<FooEntity4DeleteRelation> {
    }

    @Table(name = "foo")
    public static class FooEntity4DeleteRelation extends GenericMiddleEntity<String, String, Integer> {
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
        public IDGenerator<String> getIDGenerator() {
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
