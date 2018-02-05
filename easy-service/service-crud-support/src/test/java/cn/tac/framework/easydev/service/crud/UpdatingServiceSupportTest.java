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
@SpringBootTest(classes = UpdatingServiceSupportTest.class)
@SpringBootApplication(exclude = {MybatisAutoConfiguration.class, DataSourceAutoConfiguration.class})
public class UpdatingServiceSupportTest {
    @Autowired
    private FooService4Updating service;

    @MockBean
    private FooRepository4Updating repository;

    @Test
    public void testSimply() {
        assertThat(service).isNotNull();
        assertThat(repository).isNotNull();
    }

    @Test
    public void updateByPrimaryKeySelective() {
        FooEntity4Updating o = new FooEntity4Updating();
        when(repository.updateByPrimaryKeySelective(o)).thenReturn(0);
        assertThat(service.updateByPrimaryKeySelective(o)).isEqualTo(null);
        verify(repository, times(1)).updateByPrimaryKeySelective(o);
        when(repository.updateByPrimaryKeySelective(o)).thenReturn(1);
        assertThat(service.updateByPrimaryKeySelective(o)).isEqualTo(o);
        verify(repository, times(2)).updateByPrimaryKeySelective(o);
    }

    @Service
    public static class FooService4Updating extends ServiceSkeleton<FooEntity4Updating, String>
            implements UpdatingServiceSupport<FooEntity4Updating, String> {
        @Autowired
        public FooService4Updating(FooRepository4Updating repository) {
            super(repository);
        }
    }

    public static class FooRepository4Updating extends CrudRepositorySupport<FooEntity4Updating, String> {
        public FooRepository4Updating(CrudMapperSupport<FooEntity4Updating> mapper, Class<FooEntity4Updating> typeReference) {
            super(mapper, typeReference);
        }
    }

    public static class FooEntity4Updating extends GenericMinEntity<String> {
        @Override
        public IDGenerator<String> idGenerator() {
            return UUIDGenerator.instance();
        }
    }
}
