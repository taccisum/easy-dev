package cn.tac.framework.easydev.test.common;

import org.junit.Test;

import java.util.Random;
import java.util.regex.Pattern;

import static cn.tac.framework.easydev.test.common.CommonTestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author tac
 * @since 25/01/2018
 */
public class CommonTestUtilsTest extends CommonTestSupport {
    @Test
    public void testRange() throws Exception {
        int sum = 0;
        for (int i : range(10)) {
            sum += i;
        }
        assertThat(sum).isEqualTo(45);
        int sum1 = 0;
        for (int i : range(20, 30)) {
            sum1 += i;
        }
        assertThat(sum1).isEqualTo(245);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRangeWhenStartGreatThanEnd() {
        range(2, 1);
    }

    @Test
    public void testDivider() throws Exception {
        divider();
        assertThat(Pattern.compile("^-{50}\n$").matcher(capture.toString()).matches()).isTrue();
        capture.reset();
        divider(100);
        assertThat(Pattern.compile("^-{100}\n$").matcher(capture.toString()).matches()).isTrue();
    }

    @Test
    public void testRepeat() throws Exception {
        Random r = new Random();
        repeat(i -> assertThat(r.nextInt(100)).isLessThan(100), 10);
    }
}
