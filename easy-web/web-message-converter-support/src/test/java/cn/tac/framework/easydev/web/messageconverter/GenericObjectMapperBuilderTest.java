package cn.tac.framework.easydev.web.messageconverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author tac
 * @since 2.0
 */
public class GenericObjectMapperBuilderTest {
    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        GenericObjectMapperBuilder builder = new GenericObjectMapperBuilder();
        builder.dateFormatPattern("yyyy-MM-dd HH:mm:ss");
        builder.long2String(true);
        objectMapper = builder.build();
    }

    @Test
    public void serializer() throws JsonProcessingException {
        Foo foo = new Foo();
        foo.setField1(157928765366870016L);
        //noinspection deprecation
        foo.setField2(new Date(117, 0, 1, 12, 34, 56));
        String json = objectMapper.writeValueAsString(foo);
        System.out.println(json);
        assertThat(json).contains("\"157928765366870016\"", "2017-01-01 12:34:56");     //如果配置了long2string，这里的值应该在json中是转换成了字符串而非number
    }

    @SuppressWarnings("deprecation")
    @Test
    public void deserialize() throws Exception {
        Foo foo = objectMapper.readValue("{\"field1\": 157928765366870016, \"field2\": \"2017-01-01 12:34:56\"}", Foo.class);
        assertThat(foo).isNotNull();
        assertThat(foo.getField1()).isEqualTo(157928765366870016L);
        assertThat(foo.getField2().getYear() + 1900).isEqualTo(2017);
        assertThat(foo.getField2().getMonth() + 1).isEqualTo(1);
        assertThat(foo.getField2().getDay() + 1).isEqualTo(1);
        assertThat(foo.getField2().getHours()).isEqualTo(12);
        assertThat(foo.getField2().getMinutes()).isEqualTo(34);
        assertThat(foo.getField2().getSeconds()).isEqualTo(56);
    }

    private static class Foo {
        private Long field1;
        private Date field2;


        public Long getField1() {
            return field1;
        }

        public void setField1(Long field1) {
            this.field1 = field1;
        }

        public Date getField2() {
            return field2;
        }

        public void setField2(Date field2) {
            this.field2 = field2;
        }
    }
}
