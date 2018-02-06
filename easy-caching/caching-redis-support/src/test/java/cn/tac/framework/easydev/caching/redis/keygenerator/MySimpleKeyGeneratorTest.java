package cn.tac.framework.easydev.caching.redis.keygenerator;

import org.junit.Test;
import org.springframework.cache.annotation.Cacheable;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author tac
 * @since 2.0
 */
public class MySimpleKeyGeneratorTest {
    @Test
    public void generate() throws Exception {
        Method cacheableMethod = this.getClass().getMethod("cacheableTest", String.class);
        assertThat(new MySimpleKeyGenerator().generate(this, cacheableMethod, "233")).isEqualTo("cacheable_test:233");
    }

    @Test
    public void generate1() throws Exception {
        Method cacheableMethod = this.getClass().getMethod("cacheableMultiKeyTest", String.class, String.class);
        assertThat(new MySimpleKeyGenerator().generate(this, cacheableMethod, "233", "hhh").toString())
                .contains("cacheable_multi_key_test", "233", "hhh");
    }

    @Cacheable(value = "cacheable_test")
    public void cacheableTest(String id) {
    }

    @Cacheable(value = "cacheable_multi_key_test")
    public void cacheableMultiKeyTest(String id1, String id2) {
    }
}
