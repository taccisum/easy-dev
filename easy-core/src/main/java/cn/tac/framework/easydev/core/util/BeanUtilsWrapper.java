package cn.tac.framework.easydev.core.util;

import cn.tac.framework.easydev.core.exception.BeanUtilsException;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * BeanUtils facade
 * 1. 对bean utils的调用做封装，若后续对性能有要求，可以很方便替换成更高效的bean utils<br/>
 * 2. 可以根据需求很方便地集成不同的第三方BeanUtils方法到该类
 *
 * @author tac
 * @see org.springframework.beans.BeanUtils
 * @see org.apache.commons.beanutils.BeanUtils
 * @since 1.0
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
    public static Map<String, Object> extract(Object bean) {
        Map<String, Object> params = new HashMap<>(0);
        try {
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(bean);
            for (int i = 0; i < descriptors.length; i++) {
                String name = descriptors[i].getName();
                if (!"class".equals(name)) {
                    params.put(name, propertyUtilsBean.getNestedProperty(bean, name));
                } else {
                    //todo:: 抽取嵌套对象属性
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return params;
    }

    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }


    /**
     * trim bean中所有类型为字符串的字段
     *
     * @param bean 要操作的bean
     * @since 2.3
     */
    public static void trimAllStringField(Object bean) {
        trimAllStringField(bean, true);
    }

    /**
     * @param containNonPublic 是否包含non-public字段，false则只trim public及带有getter、setter的字段
     * @since 2.3
     */
    public static void trimAllStringField(Object bean, boolean containNonPublic) {
        try {
            if (bean != null) {
                Field[] fields = bean.getClass().getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    Field f = fields[i];
                    if (f.getType().isAssignableFrom(String.class)) {
                        String fieldName = f.getName();
                        String value = getStringFieldValue(bean, fieldName, containNonPublic);
                        if (value == null) continue;
                        setStringFieldValue(bean, fieldName, value.trim(), containNonPublic);
                    }
                }
            }
        } catch (Exception e) {
            throw new BeanUtilsException("trimAllStringField", e);
        }
    }

    private static String getStringFieldValue(Object bean, String fieldName, boolean containNonPublic) throws Exception {
        try {
            //try getter
            String methodName = "get" +
                    fieldName.substring(0, 1).toUpperCase() +
                    fieldName.substring(1);
            Method method = bean.getClass().getMethod(methodName);
            return method.invoke(bean).toString();
        } catch (NoSuchMethodException e) {
            Field field = bean.getClass().getDeclaredField(fieldName);
            if (containNonPublic || field.getModifiers() == Modifier.PUBLIC) {
                return field.get(bean).toString();
            }
            return null;
        }
    }

    private static void setStringFieldValue(Object bean, String fieldName, String value, boolean containNonPublic) throws Exception {
        try {
            // try setter
            String setterName = "set" +
                    fieldName.substring(0, 1).toUpperCase() +
                    fieldName.substring(1);
            Class[] classArr = new Class[1];
            classArr[0] = "java.lang.String".getClass();
            Method method;
            method = bean.getClass().getMethod(setterName, classArr);
            method.invoke(bean, value);
        } catch (NoSuchMethodException e) {
            Field field = bean.getClass().getDeclaredField(fieldName);
            if (containNonPublic || field.getModifiers() == Modifier.PUBLIC) {
                field.setAccessible(true);
                field.set(bean, value);
                field.setAccessible(false);
            }
        }
    }
}
