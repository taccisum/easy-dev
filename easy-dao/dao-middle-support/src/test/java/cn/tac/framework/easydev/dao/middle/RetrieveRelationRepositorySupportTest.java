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
@ActiveProfiles({"crud-relation-repository-support", "retrieve-relation-repository-support"})
@ImportAutoConfiguration(MapperAutoConfiguration.class)
public class RetrieveRelationRepositorySupportTest {
    @Autowired
    private FooRetrieveRelationRepository repository;
    @Autowired
    private FooMapper4RetrieveRelation mapper;

    @Test
    public void testSimply() {
        assertThat(repository).isNotNull();
        assertThat(mapper).isNotNull();
    }

    @Test
    public void selectAllRelation() throws Exception {
        assertThat(repository.selectAllRelation("uid_1").size()).isEqualTo(5);
    }

    @Test
    public void selectAllRelationInversely() throws Exception {
        assertThat(repository.selectAllRelationInversely(6).size()).isEqualTo(5);
    }

    @Test
    public void anyRelation() throws Exception {
        assertThat(repository.anyRelation("uid_7", 7)).isTrue();
        assertThat(repository.anyRelation("uid_7", 8)).isFalse();
        assertThat(repository.anyRelation("uid_8", 7)).isFalse();
        assertThat(repository.anyRelation("uid_8", 8)).isTrue();
    }

    @Repository
    static class FooRetrieveRelationRepository extends RepositorySkeleton<FooEntity4RetrieveRelation, String>
            implements RetrieveRelationRepositorySupport<FooEntity4RetrieveRelation, String, String, Integer> {

        public FooRetrieveRelationRepository(FooMapper4RetrieveRelation mapper) {
            super(mapper, FooEntity4RetrieveRelation.class);
        }
    }

    @Mapper
    interface FooMapper4RetrieveRelation extends CrudMapperSupport<FooEntity4RetrieveRelation> {
    }

    @Table(name = "foo")
    public static class FooEntity4RetrieveRelation extends GenericMiddleEntity<String, String, Integer> {
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
