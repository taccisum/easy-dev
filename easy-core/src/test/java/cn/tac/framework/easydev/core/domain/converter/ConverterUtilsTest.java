package cn.tac.framework.easydev.core.domain.converter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * @author tac
 * @since 05/02/2018
 */
public class ConverterUtilsTest {
    private Converter<Foo, FooDto> fooConverter;

    @Before
    public void setUp() throws Exception {
        fooConverter = mock(Converter.class);
        ConverterFactory.register(fooConverter, Foo.class, FooDto.class);
    }

    @After
    public void tearDown() throws Exception {
        ConverterFactory.clean();
    }

    @Test
    public void convert() throws Exception {
        doReturn(new FooDto()).when(fooConverter).convert(any());
        FooDto dto = ConverterUtils.convert(new Foo(), FooDto.class);
        verify(fooConverter, times(1)).convert(any());
        assertThat(dto).isNotNull();
    }

    @Test
    public void convertAll() throws Exception {
        List<FooDto> toList = new ArrayList<>();
        doReturn(toList).when(fooConverter).convertAll(any());
        List<Foo> fromList = new ArrayList<>();
        fromList.add(new Foo());
        assertThat(ConverterUtils.convertAll(fromList, FooDto.class)).isEqualTo(toList);
        verify(fooConverter, times(1)).convertAll(any());
    }

    private class Foo {
    }

    private class FooDto {
    }
}
