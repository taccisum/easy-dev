package cn.tac.framework.easydev.core.support.converter;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author tac
 * @since 22/01/2018
 */
public class GenericSingleConverterSupportByGuavaTest {

    @Test
    public void convert() {
        Foo foo = new Foo();
        foo.setField1("123");
        assertThat(new Foo2BarConverter().convert(foo).getField1()).isEqualTo("123");
    }

    @Test
    public void convertBatch() {
        List<Foo> ls = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Foo foo = new Foo();
            foo.setField1(String.valueOf(i));
        }
        int i = 0;
        Iterable<Bar> bars = new Foo2BarConverter().convertAll(ls);
        for (Bar bar : bars) {
            assertThat(bar.getField1()).isEqualTo(i);
            i++;
        }
    }

    @Test(expected = UnsupportedOperationException.class)
    public void reverse() {
        new Foo2BarConverter().reverse();
    }

    class Foo2BarConverter extends GenericSingleConverterSupportByGuava<Foo, Bar> {
        @Override
        protected Bar doForward(Foo foo) {
            Bar bar = new Bar();
            bar.setField1(foo.getField1());
            return bar;
        }
    }

    private class Foo {
        private String field1;

        public String getField1() {
            return field1;
        }

        public void setField1(String field1) {
            this.field1 = field1;
        }
    }

    private class Bar {
        private String field1;

        public String getField1() {
            return field1;
        }

        public void setField1(String field1) {
            this.field1 = field1;
        }
    }
}
