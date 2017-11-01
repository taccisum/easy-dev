package util;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author tac
 * @since 01/11/2017
 */
public class BeanUtilsWrapperTest {
    @Test
    public void copyProperties() throws Exception {
        Foo foo1 = new Foo();
        Foo foo2 = new Foo("bar1", "bar2");
        BeanUtilsWrapper.copyProperties(foo2, foo1);
        Assert.assertEquals(foo2.getBar1(), foo1.getBar1());
        Assert.assertEquals(foo2.getBar2(), foo1.getBar2());
    }

    @Test
    public void copyPropertiesSelective() throws Exception {
        Foo foo1 = new Foo();
        foo1.setBar1("abc");
        Foo foo2 = new Foo(null, "bar2");
        BeanUtilsWrapper.copyPropertiesSelective(foo2, foo1);
        Assert.assertEquals("abc", foo1.getBar1());
        Assert.assertEquals(foo2.getBar2(), foo1.getBar2());
    }

    @Test
    public void populate() throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("bar1", "bar1");
        map.put("bar2", "bar2");
        Foo foo = new Foo();
        BeanUtilsWrapper.populate(foo, map);
        Assert.assertEquals("bar1", foo.getBar1());
        Assert.assertEquals("bar2", foo.getBar2());
    }

    @Test
    public void extract() throws Exception {
        Foo foo = new Foo("bar1", "bar2");
        Map<String, Object> map = BeanUtilsWrapper.extract(foo);
        Assert.assertEquals("bar1", map.get("bar1"));
        Assert.assertEquals("bar2", map.get("bar2"));
    }

    public class Foo {
        public Foo() {
        }

        public Foo(String bar1, String bar2) {
            this.bar1 = bar1;
            this.bar2 = bar2;
        }

        private String bar1;
        private String bar2;

        public String getBar1() {
            return bar1;
        }

        public void setBar1(String bar1) {
            this.bar1 = bar1;
        }

        public String getBar2() {
            return bar2;
        }

        public void setBar2(String bar2) {
            this.bar2 = bar2;
        }
    }

}
