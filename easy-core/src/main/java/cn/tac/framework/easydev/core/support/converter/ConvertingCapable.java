package cn.tac.framework.easydev.core.support.converter;

import cn.tac.framework.easydev.core.pojo.Converter;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tac
 * @since 2.0
 */
public abstract class ConvertingCapable {
    protected <FROM, TO> TO convert(FROM from, Class<? extends FROM> fromClazz, Class<TO> toClazz) {
        return (TO) getConverter(fromClazz, toClazz).convert(from);
    }

    protected <FROM, TO> TO convert(FROM from, Class<TO> toClazz) {
        return convert(from, from.getClass(), toClazz);
    }

    protected <FROM, TO> TO convert(FROM from, TO to) {
        return (TO) convert(from, to.getClass());
    }

    protected <FROM, TO> TO convert(FROM from, Class<? extends FROM> fromClazz, TO to) {
        return (TO) convert(from, fromClazz, to.getClass());
    }

    protected <FROM, TO> List<TO> convertAll(List<FROM> fromList, Class<TO> toCls) {
        if (CollectionUtils.isEmpty(fromList)) {
            return new ArrayList<>();
        }
        return getConverter(fromList.get(0).getClass(), toCls).convertAll(fromList);
    }

    protected Converter getConverter(Class fromCls, Class toCls) {
        return ConverterFactory.get(fromCls, toCls);
    }
}
