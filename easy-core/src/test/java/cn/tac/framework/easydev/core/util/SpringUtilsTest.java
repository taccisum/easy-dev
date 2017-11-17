package cn.tac.framework.easydev.core.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author tac
 * @since 17/11/2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringUtilsTest.class)
@SpringBootApplication
@Import(SpringUtils.class)
@ActiveProfiles("spring-utils")
public class SpringUtilsTest {
    @Test
    public void getBean() throws Exception {
        assertThat(SpringUtils.getBean(MyBean.class)).isNotNull();
        assertThat(SpringUtils.getBean("myBean")).isNotNull();
    }

    @Test
    public void getProperty() throws Exception {
        assertThat(SpringUtils.getProperty("foo", null)).isEqualTo("bar");
        assertThat(SpringUtils.getProperty("foo1", "default_bar")).isEqualTo("default_bar");
    }

    @Component("myBean")
    static class MyBean{
    }
}
