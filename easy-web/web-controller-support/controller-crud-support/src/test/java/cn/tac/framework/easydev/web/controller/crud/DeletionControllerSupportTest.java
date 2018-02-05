package cn.tac.framework.easydev.web.controller.crud;

import cn.tac.framework.easydev.dao.core.pojo.GenericMinEntity;
import cn.tac.framework.easydev.dao.core.pojo.InitializingEntity;
import cn.tac.framework.easydev.dao.core.strategy.id.IDGenerator;
import cn.tac.framework.easydev.dao.core.strategy.id.UUIDGenerator;
import cn.tac.framework.easydev.dao.core.util.EntityUtils;
import cn.tac.framework.easydev.dao.crud.CrudRepositorySupport;
import cn.tac.framework.easydev.service.crud.CrudServiceSupport;
import cn.tac.framework.easydev.web.controller.core.ControllerSkeleton;
import cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.tac.framework.easydev.web.controller.utils.AssertUtils.assertSuccess;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author tac
 * @since 2.0
 */
@RunWith(SpringRunner.class)
@WebMvcTest(DeletionControllerSupportTest.FooController4Deletion.class)
public class DeletionControllerSupportTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private FooService4Deletion service;

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testSimply() {
        assertThat(service).isNotNull();
    }

    @Test
    public void testDelete() throws Exception {
        when(service.deleteByPrimaryKey("123")).thenReturn(1);

        String responseStr = mvc.perform(delete("/foo/123")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        RestfulApiResponse response = objectMapper.readValue(responseStr, RestfulApiResponse.class);
        assertSuccess(response);
        assertThat(response.getData()).isEqualTo(1);
    }

    @RestController
    @RequestMapping("foo")
    public static class FooController4Deletion extends ControllerSkeleton<FooEntity4Deletion, String>
            implements DeletionControllerSupport<FooEntity4Deletion, String> {
        @Autowired
        public FooController4Deletion(FooService4Deletion service) {
            super(service);
        }
    }

    public static class FooService4Deletion extends CrudServiceSupport<FooEntity4Deletion, String> {
        public FooService4Deletion(CrudRepositorySupport<FooEntity4Deletion, String> repository) {
            super(repository);
        }
    }

    public static class FooEntity4Deletion extends GenericMinEntity<String> implements InitializingEntity {
        private String bar1;

        public String getBar1() {
            return bar1;
        }

        public void setBar1(String bar1) {
            this.bar1 = bar1;
        }

        @Override
        public IDGenerator<String> idGenerator() {
            return UUIDGenerator.instance();
        }

        @Override
        public void init() {
            EntityUtils.init(this);
        }
    }
}
