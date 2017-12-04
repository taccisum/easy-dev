package cn.tac.framework.easydev.web.core.builder;

import cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse;
import org.junit.Test;

import static cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author tac
 * @since 04/12/2017
 */
public class RestfulApiResponseBuilderTest {
    @Test
    public void success() throws Exception {
        RestfulApiResponse<Foo> r = RestfulApiResponseBuilder.success(Foo.class)
                .msg("success")
                .data(new Foo("123"))
                .build();
        assertThat(r.getState()).isEqualTo(SUCCESS_STATE);
        assertThat(r.getCode()).isEqualTo(SUCCESS_CODE);
        assertThat(r.getMsg()).isEqualTo("success");
        assertThat(r.getData().getBar1()).isEqualTo("123");
    }

    @Test
    public void failure() throws Exception {
        RestfulApiResponse r = RestfulApiResponseBuilder.failure()
                .msg("failure")
                .code("233")
                .build();
        assertThat(r.getState()).isEqualTo(FAILURE_STATE);
        assertThat(r.getCode()).isEqualTo("233");
        assertThat(r.getMsg()).isEqualTo("failure");
    }

    @Test
    public void error() throws Exception {
        RestfulApiResponse r = RestfulApiResponseBuilder.error()
                .msg("error")
                .stackTrack("null point exception")
                .build();
        assertThat(r.getState()).isEqualTo(ERROR_STATE);
        assertThat(r.getCode()).isEqualTo(ERROR_CODE);
        assertThat(r.getMsg()).isEqualTo("error");
        assertThat(r.getStackTrace()).isEqualTo("null point exception");
    }

    static class Foo {
        public Foo(String bar1) {
            this.bar1 = bar1;
        }

        private String bar1;

        public String getBar1() {
            return bar1;
        }

        public void setBar1(String bar1) {
            this.bar1 = bar1;
        }
    }
}
