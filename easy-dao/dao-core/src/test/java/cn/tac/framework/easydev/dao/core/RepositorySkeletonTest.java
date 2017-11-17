package cn.tac.framework.easydev.dao.core;

import cn.tac.framework.easydev.dao.core.pojo.GenericEntity;
import cn.tac.framework.easydev.dao.core.strategy.id.IDGenerator;
import cn.tac.framework.easydev.dao.core.strategy.id.UUIDGenerator;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author tac
 * @since 2.0
 */
public class RepositorySkeletonTest {
    @Test
    public void getEntityClass() throws Exception {
        assertThat(new MyRepository().getEntityClass()).isEqualTo(MyEntity.class);
    }

    @Test
    public void newEntityInstance() throws Exception {
        assertThat(new MyRepository().newEntityInstance().getClass()).isEqualTo(MyEntity.class);
        assertThat(new MyRepository().newEntityInstance()).isNotEqualTo(new MyRepository().newEntityInstance());
    }

    static class MyRepository extends RepositorySkeleton<MyEntity, String> {
        MyRepository() {
            super(null, MyEntity.class);
        }
    }

    static class MyEntity extends GenericEntity<String> {
        @Override
        public IDGenerator<String> getIDGenerator() {
            return UUIDGenerator.instance();
        }
    }
}
