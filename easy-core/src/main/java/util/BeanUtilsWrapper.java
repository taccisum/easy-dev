package util;

import cn.tac.framework.easydev.core.exception.BeanUtilsException;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * todo:: 编写单元测试
 *
 * BeanUtils facade
 * 1. 对bean utils的调用做封装，若后续对性能有要求，可以很方便替换成更高效的bean utils<br/>
 * 2. 可以根据需求很方便地集成不同的第三方BeanUtils方法到该类
 *
 * @see org.springframework.beans.BeanUtils
 * @see org.apache.commons.beanutils.BeanUtils
 *
 * @author : tac
 * @since : 2017/11/1
 */

public abstract class BeanUtilsWrapper {
    public static void copyProperties(Object source, Object target) {
        org.springframework.beans.BeanUtils.copyProperties(source, target);
    }

    public static void copyPropertiesSelective(Object source, Object target) {
        org.springframework.beans.BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    public static void populate(Object bean, Map<String, ? extends Object> properties) {
        try {
            org.apache.commons.beanutils.BeanUtils.populate(bean, properties);
        } catch (Exception e) {
            throw new BeanUtilsException("populate", e);
        }
    }

    /**
     * 将bean的字段抽取出来作为一个map，暂不支持嵌套对象的抽取（嵌套对象的字段会被忽略）
     */
    public static Map<String, Object> extract(Object bean){
        Map<String, Object> params = new HashMap<>(0);
        try {
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(bean);
            for (int i = 0; i < descriptors.length; i++) {
                String name = descriptors[i].getName();
                if (!"class".equals(name)) {
                    params.put(name, propertyUtilsBean.getNestedProperty(bean, name));
                }else{
                    //todo:: 抽取嵌套对象属性
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return params;
    }

    private static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for(PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
