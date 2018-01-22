package cn.tac.framework.easydev.core.pojo;

import java.util.List;

/**
 * @author tac
 * @since 22/01/2018
 */
public interface Converter<FROM, TO> {
    TO convert(FROM from);

    List<TO> convertAll(List<FROM> from);
}
