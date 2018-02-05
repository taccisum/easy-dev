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

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author tac
 * @since 02/12/2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RetrievalServiceSupportTest.class)
@SpringBootApplication(exclude = {MybatisAutoConfiguration.class, DataSourceAutoConfiguration.class})
public class RetrievalServiceSupportTest {
    @Autowired
    private FooService4Retrieval service;

    @MockBean
    private FooRepository4Retrieval repository;

    @Test
    public void testSimply() {
        assertThat(service).isNotNull();
        assertThat(repository).isNotNull();
    }

    @Test
    public void selectByPrimaryKey() throws Exception {
        String id = UUIDGenerator.instance().generate();
        FooEntity4Retrieval entity = new FooEntity4Retrieval();
        when(repository.selectByPrimaryKey(id)).thenReturn(entity);
        assertThat(service.selectByPrimaryKey(id)).isEqualTo(entity);
        verify(repository, times(1)).selectByPrimaryKey(id);
    }

    @Test
    public void select() throws Exception {
        FooEntity4Retrieval o = new FooEntity4Retrieval();
        List<FooEntity4Retrieval> ls = new ArrayList<>();
        when(repository.select(o)).thenReturn(ls);
        assertThat(service.select(o)).isEqualTo(ls);
        verify(repository, times(1)).select(o);
    }

    @Test
    public void selectAll() throws Exception {
        List<FooEntity4Retrieval> ls = new ArrayList<>();
        when(repository.selectAll()).thenReturn(ls);
        assertThat(service.selectAll()).isEqualTo(ls);
        verify(repository, times(1)).selectAll();
    }

    @Service
    public static class FooService4Retrieval extends ServiceSkeleton<FooEntity4Retrieval, String>
            implements RetrievalServiceSupport<FooEntity4Retrieval, String> {
        @Autowired
        public FooService4Retrieval(FooRepository4Retrieval repository) {
            super(repository);
        }
    }

    public static class FooRepository4Retrieval extends CrudRepositorySupport<FooEntity4Retrieval, String> {
        public FooRepository4Retrieval(CrudMapperSupport<FooEntity4Retrieval> mapper, Class<FooEntity4Retrieval> typeReference) {
            super(mapper, typeReference);
        }
    }

    public static class FooEntity4Retrieval extends GenericMinEntity<String> {
        @Override
        public IDGenerator<String> idGenerator() {
            return UUIDGenerator.instance();
        }
    }
}
