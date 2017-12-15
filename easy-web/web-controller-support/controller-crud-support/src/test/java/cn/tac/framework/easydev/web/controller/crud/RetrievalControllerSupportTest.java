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
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author tac
 * @since 2.0
 */
@RunWith(SpringRunner.class)
@WebMvcTest(RetrievalControllerSupportTest.FooController4Retrieval.class)
public class RetrievalControllerSupportTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private FooService4Retrieval service;

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testSimply() {
        assertThat(service).isNotNull();
    }

    @Test
    public void testGet() throws Exception {
        FooEntity4Retrieval o = new FooEntity4Retrieval("bar1");
        when(service.selectByPrimaryKey("123")).thenReturn(o);

        String responseStr = mvc.perform(get("/foo/123")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        RestfulApiResponse response = objectMapper.readValue(responseStr, RestfulApiResponse.class);
        assertSuccess(response);
    }

    @RestController
    @RequestMapping("foo")
    public static class FooController4Retrieval extends ControllerSkeleton<FooEntity4Retrieval, String>
            implements RetrievalControllerSupport<FooEntity4Retrieval, String> {
        @Autowired
        public FooController4Retrieval(FooService4Retrieval service) {
            super(service);
        }
    }

    public static class FooService4Retrieval extends CrudServiceSupport<FooEntity4Retrieval, String> {
        public FooService4Retrieval(CrudRepositorySupport<FooEntity4Retrieval, String> repository) {
            super(repository);
        }
    }

    public static class FooEntity4Retrieval extends GenericMinEntity<String> implements InitializingEntity {
        public FooEntity4Retrieval() {
        }

        public FooEntity4Retrieval(String bar1) {
            init();
            this.bar1 = bar1;
        }

        private String bar1;

        public String getBar1() {
            return bar1;
        }

        public void setBar1(String bar1) {
            this.bar1 = bar1;
        }

        @Override
        public IDGenerator<String> getIDGenerator() {
            return UUIDGenerator.instance();
        }

        @Override
        public void init() {
            EntityUtils.init(this);
        }
    }
}
