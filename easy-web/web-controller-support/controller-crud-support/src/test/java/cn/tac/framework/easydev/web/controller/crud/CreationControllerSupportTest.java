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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author tac
 * @since 2.0
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CreationControllerSupportTest.FooController4Creation.class)
public class CreationControllerSupportTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private FooService4Creation service;

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testSimply() {
        assertThat(service).isNotNull();
    }

    @Test
    public void insert() throws Exception {
        FooModel4Creation o = new FooModel4Creation();
        o.setBar1("bar1");
        String content = objectMapper.writeValueAsString(o);

        when(service.insert(any())).then(invocationOnMock -> {
            FooEntity4Creation entity = invocationOnMock.getArgumentAt(0, FooEntity4Creation.class);
            entity.init();
            return entity;
        });

        String responseStr = mvc.perform(post("/foo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        assertSuccess(objectMapper.readValue(responseStr, RestfulApiResponse.class));
    }

    @RestController
    @RequestMapping("foo")
    public static class FooController4Creation extends ControllerSkeleton<FooEntity4Creation, String>
            implements CreationControllerSupport<FooEntity4Creation, String, FooModel4Creation> {
        @Autowired
        public FooController4Creation(FooService4Creation service) {
            super(service);
        }

        @Override
        public FooEntity4Creation convertCreationModel2Entity(FooModel4Creation model) {
            FooEntity4Creation o = new FooEntity4Creation();
            o.setBar1(model.getBar1());
            return o;
        }
    }

    public static class FooService4Creation extends CrudServiceSupport<FooEntity4Creation, String> {
        public FooService4Creation(CrudRepositorySupport<FooEntity4Creation, String> repository) {
            super(repository);
        }
    }

    public static class FooEntity4Creation extends GenericMinEntity<String> implements InitializingEntity {
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

    public static class FooModel4Creation {
        private String bar1;

        public String getBar1() {
            return bar1;
        }

        public void setBar1(String bar1) {
            this.bar1 = bar1;
        }
    }
}
