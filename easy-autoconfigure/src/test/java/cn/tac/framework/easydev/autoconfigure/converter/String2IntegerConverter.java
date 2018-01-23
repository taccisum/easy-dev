package cn.tac.framework.easydev.autoconfigure.converter;

import cn.tac.framework.easydev.core.domain.converter.GenericSingleConverterSupportByGuava;
import cn.tac.framework.easydev.core.domain.converter.annotation.Register2Factory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author tac
 * @since 2.0
 */

@Component
@Register2Factory(from = String.class, to = Integer.class)
public class String2IntegerConverter extends GenericSingleConverterSupportByGuava<String, Integer> {
    @Value("${converter.string2int.increase}")
    private Integer increase;

    @Override
    protected Integer doForward(String s) {
        return Integer.parseInt(s) + increase;
    }
}

