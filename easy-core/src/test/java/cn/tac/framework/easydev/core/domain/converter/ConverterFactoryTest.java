package cn.tac.framework.easydev.core.domain.converter;

import cn.tac.framework.easydev.core.exception.NoSuchConverterException;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author tac
 * @since 2.0
 */
public class ConverterFactoryTest {

    @After
    public void tearDown() throws Exception {
        ConverterFactory.clean();
    }

    @Rule
    public OutputCapture capture = new OutputCapture();

    @Test
    public void registerAndFind() throws Exception {
        ConverterFactory.register(new A2BConverter(), A.class, B.class);
        assertThat(ConverterFactory.find(A.class, B.class)).isInstanceOf(A2BConverter.class);
    }

    @Test
    public void repeatRegister() throws Exception {
        ConverterFactory.register(new A2BConverter(), A.class, B.class);
        ConverterFactory.register(new A2BConverter(), A.class, B.class);
        assertThat(capture.toString()).contains("WARN", "redundant converter", A.class.toString(), B.class.toString());
    }

    @Test(expected = NoSuchConverterException.class)
    public void clean() {
        ConverterFactory.register(new A2BConverter(), A.class, B.class);
        assertThat(ConverterFactory.find(A.class, B.class)).isInstanceOf(A2BConverter.class);
        ConverterFactory.clean();
        ConverterFactory.find(A.class, B.class);
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
