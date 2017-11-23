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
@ActiveProfiles({"crud-relation-repository-support", "create-relation-repository-support"})
@ImportAutoConfiguration(MapperAutoConfiguration.class)
public class CreateRelationRepositorySupportTest {
    @Autowired
    private FooCreateRelationRepository repository;
    @Autowired
    private FooMapper4CreateRelation mapper;

    @Test
    public void testSimply() {
        assertThat(repository).isNotNull();
        assertThat(mapper).isNotNull();
        System.out.println(mapper.selectAll());
    }

    @Test
    public void relate() throws Exception {
        assertThat(repository.relate("uid_1", 1, 2, 3, 4, 5)).isEqualTo(5);
        assertThat(mapper.selectAll().size()).isEqualTo(5);
        FooEntity4CreateRelation o = new FooEntity4CreateRelation();
        o.setSourceId("uid_1");
        assertThat(mapper.select(o).size()).isEqualTo(5);
        FooEntity4CreateRelation o1 = new FooEntity4CreateRelation();
        o1.setTargetId(5);
        assertThat(mapper.select(o1).size()).isEqualTo(1);
    }

    @Test
    public void relateInversely() throws Exception {
        assertThat(repository.relateInversely(1, "uid_1", "uid_2", "uid_3", "uid_4")).isEqualTo(4);
        assertThat(mapper.selectAll().size()).isEqualTo(4);
        FooEntity4CreateRelation o1 = new FooEntity4CreateRelation();
        o1.setTargetId(1);
        assertThat(mapper.select(o1).size()).isEqualTo(4);
        FooEntity4CreateRelation o = new FooEntity4CreateRelation();
        o.setSourceId("uid_1");
        assertThat(mapper.select(o).size()).isEqualTo(1);
    }

    @Repository
    static class FooCreateRelationRepository extends RepositorySkeleton<FooEntity4CreateRelation, String>
            implements CreateRelationRepositorySupport<FooEntity4CreateRelation, String, String, Integer> {

        public FooCreateRelationRepository(FooMapper4CreateRelation mapper) {
            super(mapper, FooEntity4CreateRelation.class);
        }
    }

    @Mapper
    interface FooMapper4CreateRelation extends CrudMapperSupport<FooEntity4CreateRelation>{
    }

    @Table(name = "foo")
    public static class FooEntity4CreateRelation extends GenericMiddleEntity<String, String, Integer>{
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
