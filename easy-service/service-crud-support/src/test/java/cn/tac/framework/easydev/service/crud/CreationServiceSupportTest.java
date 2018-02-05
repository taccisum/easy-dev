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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author tac
 * @since 02/12/2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CreationServiceSupportTest.class)
@SpringBootApplication(exclude = {MybatisAutoConfiguration.class, DataSourceAutoConfiguration.class})
public class CreationServiceSupportTest {
    @Autowired
    private FooService4Creation service;

    @MockBean
    private FooRepository4Creation repository;

    @Test
    public void testSimply() {
        assertThat(service).isNotNull();
        assertThat(repository).isNotNull();
    }

    @Test
    public void insert() throws Exception {
        FooEntity4Creation o = new FooEntity4Creation();
        when(repository.insert(o)).thenReturn(0);
        assertThat(service.insert(o)).isEqualTo(null);
        verify(repository, times(1)).insert(o);
        when(repository.insert(o)).thenReturn(1);
        assertThat(service.insert(o)).isEqualTo(o);
        verify(repository, times(2)).insert(o);
    }

    @Test
    public void insertSelective() throws Exception {
        FooEntity4Creation o = new FooEntity4Creation();
        when(repository.insertSelective(o)).thenReturn(0);
        assertThat(service.insertSelective(o)).isEqualTo(null);
        verify(repository, times(1)).insertSelective(o);
        when(repository.insertSelective(o)).thenReturn(1);
        assertThat(service.insertSelective(o)).isEqualTo(o);
        verify(repository, times(2)).insertSelective(o);
    }

    @Service
    public static class FooService4Creation extends ServiceSkeleton<FooEntity4Creation, String>
            implements CreationServiceSupport<FooEntity4Creation, String> {
        @Autowired
        public FooService4Creation(FooRepository4Creation repository) {
            super(repository);
        }
    }

    public static class FooRepository4Creation extends CrudRepositorySupport<FooEntity4Creation, String> {
        public FooRepository4Creation(CrudMapperSupport<FooEntity4Creation> mapper, Class<FooEntity4Creation> typeReference) {
            super(mapper, typeReference);
        }
    }

    public static class FooEntity4Creation extends GenericMinEntity<String> {
        @Override
        public IDGenerator<String> idGenerator() {
            return UUIDGenerator.instance();
        }
    }
}
