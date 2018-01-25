package cn.tac.framework.easydev.test.common;

import io.codearte.jfairy.Fairy;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.rule.OutputCapture;

/**
 * @author tac
 * @since 2.0
 */
public abstract class CommonTestSupport {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Rule
    public OutputCapture capture = new OutputCapture();

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected Fairy fairy = Fairy.create();

    @After
    public void tearDown() throws Exception {
        capture.reset();
    }
}
