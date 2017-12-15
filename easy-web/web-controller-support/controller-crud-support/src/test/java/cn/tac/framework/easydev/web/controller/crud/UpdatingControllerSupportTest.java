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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author tac
 * @since 2.0
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UpdatingControllerSupportTest.FooController4Updating.class)
public class UpdatingControllerSupportTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private FooService4Updating service;

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testSimply() {
        assertThat(service).isNotNull();
    }

    @Test
    public void update() throws Exception {
        FooModel4Updating o = new FooModel4Updating();
        o.setBar1("bar1");
        String content = objectMapper.writeValueAsString(o);

        when(service.updateByPrimaryKeySelective(any()))
                .then(invocationOnMock -> invocationOnMock.getArgumentAt(0, FooEntity4Updating.class));

        String responseStr = mvc.perform(put("/foo/123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        RestfulApiResponse response = objectMapper.readValue(responseStr, RestfulApiResponse.class);
        assertSuccess(response);
    }

    @RestController
    @RequestMapping("foo")
    public static class FooController4Updating extends ControllerSkeleton<FooEntity4Updating, String>
            implements UpdatingControllerSupport<FooEntity4Updating, String, FooModel4Updating> {
        @Autowired
        public FooController4Updating(FooService4Updating service) {
            super(service);
        }

        @Override
        public FooEntity4Updating convertUpdatingModel2Entity(String id, FooModel4Updating model) {
            FooEntity4Updating o = new FooEntity4Updating();
            o.setId(id);
            o.setBar1(model.getBar1());
            return o;
        }
    }

    public static class FooService4Updating extends CrudServiceSupport<FooEntity4Updating, String> {
        public FooService4Updating(CrudRepositorySupport<FooEntity4Updating, String> repository) {
            super(repository);
        }
    }

    public static class FooEntity4Updating extends GenericMinEntity<String> implements InitializingEntity {
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

    public static class FooModel4Updating {
        private String bar1;

        public String getBar1() {
            return bar1;
        }

        public void setBar1(String bar1) {
            this.bar1 = bar1;
        }
    }
}
