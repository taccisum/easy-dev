package cn.tac.framework.easydev.core.domain.converter;

import java.util.List;

/**
 * @author tac
 * @since 1.0
 */
public interface Converter<FROM, TO> {
    TO convert(FROM from);

    List<TO> convertAll(List<FROM> from);
}
