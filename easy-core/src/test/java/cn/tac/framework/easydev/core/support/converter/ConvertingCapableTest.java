package cn.tac.framework.easydev.core.support.converter;

import cn.tac.framework.easydev.core.pojo.Converter;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * @author tac
 * @since 2.0
 */
public class ConvertingCapableTest {
    private FooService fooService;
    private Converter<Foo, FooDto> fooConverter;

    @Before
    public void setUp() throws Exception {
        fooService = new FooService();
        fooConverter = mock(Converter.class);
    }

    @Test
    public void convert() throws Exception {
        doReturn(new FooDto()).when(fooConverter).convert(any());
        FooDto dto = fooService.convert(new Foo(), FooDto.class);
        verify(fooConverter, times(1)).convert(any());
        assertThat(dto).isNotNull();
    }

    @Test
    public void convertAll() throws Exception {
        List<FooDto> toList = new ArrayList<>();
        doReturn(toList).when(fooConverter).convertAll(any());
        List<Foo> fromList = new ArrayList<>();
        fromList.add(new Foo());
        assertThat(fooService.convertAll(fromList, FooDto.class)).isEqualTo(toList);
        verify(fooConverter, times(1)).convertAll(any());
    }

    class FooService extends ConvertingCapable {
        @Override
        public Converter getConverter(Class fromCls, Class toCls) {
            return fooConverter;
        }
    }

    private class Foo {
    }

    private class FooDto {
    }
}
