package cn.tac.framework.easydev.service.crud;

import cn.tac.framework.easydev.dao.core.CrudMapperSupport;
import cn.tac.framework.easydev.dao.core.pojo.GenericMinEntity;
import cn.tac.framework.easydev.dao.core.strategy.id.IDGenerator;
import cn.tac.framework.easydev.dao.core.strategy.id.UUIDGenerator;
import cn.tac.framework.easydev.dao.crud.CrudRepositorySupport;
import cn.tac.framework.easydev.service.core.ServiceSkeleton;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author tac
 * @since 02/12/2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DeletionServiceSupportTest.class)
@SpringBootApplication(exclude = {MybatisAutoConfiguration.class, DataSourceAutoConfiguration.class})
public class DeletionServiceSupportTest {
    @Autowired
    private FooService4Deletion service;

    @MockBean
    private FooRepository4Deletion repository;

    @Test
    public void testSimply() {
        assertThat(service).isNotNull();
        assertThat(repository).isNotNull();
    }

    @Test
    public void delete() throws Exception {
        String id = UUIDGenerator.instance().generate();
        when(repository.deleteByPrimaryKey(id)).thenReturn(1);
        assertThat(service.deleteByPrimaryKey(id)).isEqualTo(1);
        verify(repository, times(1)).deleteByPrimaryKey(id);

        when(repository.deleteByPrimaryKey(id)).thenReturn(0);
        assertThat(service.deleteByPrimaryKey(id)).isEqualTo(0);
        verify(repository, times(2)).deleteByPrimaryKey(id);
    }

    @Service
    public static class FooService4Deletion extends ServiceSkeleton<FooEntity4Deletion, String>
            implements DeletionServiceSupport<FooEntity4Deletion, String> {
        @Autowired
        public FooService4Deletion(FooRepository4Deletion repository) {
            super(repository);
        }
    }

    public static class FooRepository4Deletion extends CrudRepositorySupport<FooEntity4Deletion, String> {
        public FooRepository4Deletion(CrudMapperSupport<FooEntity4Deletion> mapper, Class<FooEntity4Deletion> typeReference) {
            super(mapper, typeReference);
        }
    }

    public static class FooEntity4Deletion extends GenericMinEntity<String> {
        @Override
        public IDGenerator<String> idGenerator() {
            return UUIDGenerator.instance();
        }
    }
}
