package cn.tac.framework.easydev.web.core.builder;

import cn.tac.framework.easydev.core.pojo.AppCode;
import cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse;
import org.junit.Test;

import static cn.tac.framework.easydev.web.core.pojo.RestfulApiResponse.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author tac
 * @since 2.0
 */
public class RestfulApiResponseBuilderTest {
    @Test
    public void success() throws Exception {
        RestfulApiResponse<Foo> r = RestfulApiResponseBuilder.success(Foo.class)
                .msg("success")
                .friendlyMsg("you are succeed")
                .data(new Foo("123"))
                .build();
        assertThat(r.getMsg()).isEqualTo("success");
        assertThat(r.getFriendlyMsg()).isEqualTo("you are succeed");
        assertThat(r.getState()).isEqualTo(SUCCESS_STATE);
        assertThat(r.getData()).isNotNull();
        assertThat(r.getData().getBar1()).isEqualTo("123");
    }

    @Test
    public void failure() throws Exception {
        RestfulApiResponse r = RestfulApiResponseBuilder.failure()
                .msg("failure")
                .friendlyMsg("you are failed")
                .code("233")
                .build();
        assertThat(r.getMsg()).isEqualTo("failure");
        assertThat(r.getFriendlyMsg()).isEqualTo("you are failed");
        assertThat(r.getState()).isEqualTo(FAILURE_STATE);
        assertThat(r.getCode()).isEqualTo("233");
        assertThat(r.getMsg()).isEqualTo("failure");
    }

    @Test
    public void error() throws Exception {
        RestfulApiResponse r = RestfulApiResponseBuilder.error()
                .msg("error")
                .friendlyMsg("an system error happened")
                .stackTrack("null point exception")
                .build();
        assertThat(r.getMsg()).isEqualTo("error");
        assertThat(r.getFriendlyMsg()).isEqualTo("an system error happened");
        assertThat(r.getState()).isEqualTo(ERROR_STATE);
        assertThat(r.getCode()).isEqualTo(AppCode.SYSTEM_EXCEPTION_CODE);
        assertThat(r.getStackTrace()).isEqualTo("null point exception");
    }

    @Test
    public void generic() {
        RestfulApiResponse<Foo> r = RestfulApiResponseBuilder.generic(Foo.class, 99, "99")
                .msg("special response")
                .friendlyMsg("it's a special response")
                .data(new Foo("123"))
                .stackTrack("this is stack track")
                .build();
        assertThat(r.getMsg()).isEqualTo("special response");
        assertThat(r.getFriendlyMsg()).isEqualTo("it's a special response");
        assertThat(r.getState()).isEqualTo(99);
        assertThat(r.getCode()).isEqualTo("99");
        assertThat(r.getStackTrace()).isEqualTo("this is stack track");
        assertThat(r.getData()).isNotNull();
        assertThat(r.getData().getBar1()).isEqualTo("123");
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
