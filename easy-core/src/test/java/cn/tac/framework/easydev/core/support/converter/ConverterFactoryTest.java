package cn.tac.framework.easydev.core.support.converter;

import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author tac
 * @since 2.0
 */
public class ConverterFactoryTest {
    @Rule
    public OutputCapture capture = new OutputCapture();

    @Test
    public void put() throws Exception {
        ConverterFactory.put(new A2BConverter(), A.class, B.class);
        ConverterFactory.put(new A2BConverter(), A.class, B.class);
        assertThat(capture.toString()).contains("WARN", "redundant converter", A.class.toString(), B.class.toString());
        assertThat(ConverterFactory.get(A.class, B.class)).isInstanceOf(A2BConverter.class);
    }

    class A2BConverter extends GenericSingleConverterSupportByGuava<A, B> {
        @Override
        protected B doForward(A a) {
            B b = new B();
            return b;
        }
    }

    class A {
    }

    class B {
    }
}
