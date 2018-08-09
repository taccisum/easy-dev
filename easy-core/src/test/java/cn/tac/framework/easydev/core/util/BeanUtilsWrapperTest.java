package cn.tac.framework.easydev.core.util;

import com.google.common.base.MoreObjects;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author tac
 * @since 2.0
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

    @Test
    public void trimAllString() throws Exception {
        Foo4Trim foo = new Foo4Trim(" 1 ", " 2 ", " 3 ", " 4 ", 5);
        BeanUtilsWrapper.trimAllStringField(foo);
        assertThat(foo.getBar1()).isEqualTo("1");
        assertThat(foo.bar2).isEqualTo("2");
        assertThat(foo.bar3).isEqualTo("3");
        assertThat(foo.bar4).isEqualTo("4");
        assertThat(foo.bar5).isEqualTo(5);
    }

    @Test
    public void trimAllStringWhenFieldNull() throws Exception {
        Foo4Trim foo = new Foo4Trim(null, " 2 ", " 3 ", " 4 ", 5);
        BeanUtilsWrapper.trimAllStringField(foo);
        assertThat(foo.getBar1()).isEqualTo(null);
        assertThat(foo.bar2).isEqualTo("2");
        assertThat(foo.bar3).isEqualTo("3");
        assertThat(foo.bar4).isEqualTo("4");
        assertThat(foo.bar5).isEqualTo(5);
    }

    @Test
    public void trimAllStringWithoutNonPublic() throws Exception {
        Foo4Trim foo = new Foo4Trim(" 1 ", " 2 ", " 3 ", " 4 ", 5);
        BeanUtilsWrapper.trimAllStringField(foo, false);
        assertThat(foo.getBar1()).isEqualTo("1");
        assertThat(foo.bar2).isEqualTo(" 2 ");
        assertThat(foo.bar3).isEqualTo(" 3 ");
        assertThat(foo.bar4).isEqualTo("4");
    }

    @Test
    public void trimAllStringWhenBeanBaseDataType() throws Exception {
        BeanUtilsWrapper.trimAllStringField("  123  ");
        BeanUtilsWrapper.trimAllStringField(123);
        BeanUtilsWrapper.trimAllStringField(true);
    }

    @Test
    public void trimAllStringWhenArgNull() throws Exception {
        BeanUtilsWrapper.trimAllStringField(null);
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

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("bar1", bar1)
                    .add("bar2", bar2)
                    .toString();
        }
    }

    public static class Foo4Trim {
        private String bar1;
        protected String bar2;
        String bar3;
        public String bar4;
        private Integer bar5;

        public Foo4Trim(String bar1) {
            this.bar1 = bar1;
        }

        public Foo4Trim(String bar1, String bar2, String bar3, String bar4, Integer bar5) {
            this.bar1 = bar1;
            this.bar2 = bar2;
            this.bar3 = bar3;
            this.bar4 = bar4;
            this.bar5 = bar5;
        }

        public String getBar1() {
            return bar1;
        }

        public void setBar1(String bar1) {
            this.bar1 = bar1;
        }

        public Integer getBar5() {
            return bar5;
        }

        public void setBar5(Integer bar5) {
            this.bar5 = bar5;
        }
    }
}
