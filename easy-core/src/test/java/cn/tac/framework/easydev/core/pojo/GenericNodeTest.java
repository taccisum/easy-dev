package cn.tac.framework.easydev.core.pojo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author tac
 * @since 2.3
 */
public class GenericNodeTest {
    @Test
    public void testSerialize() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        /**
         * 测试数据结构如下，*号表示选中，#表示半选中
         *       1#
         *     /   \
         *    2*    3
         *    |
         *    4*
         */
        GenericIntegerSelectedCapableNode<Integer, Foo> root = new GenericIntegerSelectedCapableNode<>(new Foo(1, null, "root"), null, 0, 2);
        GenericIntegerSelectedCapableNode<Integer, Foo> children1 = new GenericIntegerSelectedCapableNode<>(new Foo(2, root.getId(), "root_children1"), root, 1, 1);
        GenericIntegerSelectedCapableNode<Integer, Foo> children2 = new GenericIntegerSelectedCapableNode<>(new Foo(3, root.getId(), "root_children2"), root, 1, 0);
        GenericIntegerSelectedCapableNode<Integer, Foo> children11 = new GenericIntegerSelectedCapableNode<>(new Foo(4, root.getId(), "root_children1_1"), root, 2, 1);
        root.addChild(children1);
        root.addChild(children2);
        children1.addChild(children11);
        String expectJson = "{\"id\":1,\"parentId\":null,\"level\":0,\"data\":{\"filed1\":\"root\"},\"selected\":2,\"children\":[{\"id\":2,\"parentId\":1,\"level\":1,\"data\":{\"filed1\":\"root_children1\"},\"selected\":1,\"children\":[{\"id\":4,\"parentId\":1,\"level\":2,\"data\":{\"filed1\":\"root_children1_1\"},\"selected\":1,\"children\":null}]},{\"id\":3,\"parentId\":1,\"level\":1,\"data\":{\"filed1\":\"root_children2\"},\"selected\":0,\"children\":null}]}";
        assertThat(objectMapper.writeValueAsString(root)).isEqualTo(expectJson);
    }

    private static class Foo implements ParentInfoAware<Integer> {
        private Integer id;
        private Integer parentId;
        private String filed1;

        public Foo(Integer id, Integer parentId, String filed1) {
            this.id = id;
            this.parentId = parentId;
            this.filed1 = filed1;
        }

        @Override
        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        @Override
        public Integer getParentId() {
            return parentId;
        }

        public void setParentId(Integer parentId) {
            this.parentId = parentId;
        }

        public String getFiled1() {
            return filed1;
        }

        public void setFiled1(String filed1) {
            this.filed1 = filed1;
        }
    }
}
