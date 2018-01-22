package cn.tac.framework.easydev.core.support.converter;

import cn.tac.framework.easydev.core.pojo.Converter;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tac
 * @since 22/01/2018
 */
public interface ConvertingCapable {
    default <FROM, TO> TO convert(FROM from, Class<? extends FROM> fromClazz, Class<TO> toClazz) {
        return (TO) getConverter(fromClazz, toClazz).convert(from);
    }

    default <FROM, TO> TO convert(FROM from, Class<TO> toClazz) {
        return convert(from, from.getClass(), toClazz);
    }

    default <FROM, TO> TO convert(FROM from, TO to) {
        return (TO) convert(from, to.getClass());
    }

    default <FROM, TO> TO convert(FROM from, Class<? extends FROM> fromClazz, TO to) {
        return (TO) convert(from, fromClazz, to.getClass());
    }

    default <FROM, TO> List<TO> convertAll(List<FROM> fromList, Class<TO> toCls) {
        if (CollectionUtils.isEmpty(fromList)) {
            return new ArrayList<>();
        }
        return getConverter(fromList.get(0).getClass(), toCls).convertAll(fromList);
    }

    default Converter getConverter(Class fromCls, Class toCls) {
        return ConverterFactory.get(fromCls, toCls);
    }
}
