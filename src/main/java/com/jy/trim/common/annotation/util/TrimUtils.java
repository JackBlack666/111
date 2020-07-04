package com.jy.trim.common.annotation.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 去除字符串前后空格工具类
 *
 * @author JinChunZhao
 * @version 1.0
 * @date 2020-07-02 22:57
 */
@Slf4j
public class TrimUtils {

    /**
     * 非字符串类型的原始数据类型
     */
    private static final List<Class<?>> WRAPPER = Arrays.asList(Byte.class, Short.class,
            Integer.class, Long.class, Float.class, Double.class, Boolean.class);

    /**
     * 符串类型的原始数据类型
     */
    private static final List<Class<?>> STRING_PER = Arrays.asList(Character.class, String.class);

    /**
     * 处理所有类型参数:基本数据类型、实体对象、List、数组、set、map，去除字符串首尾空格
     *
     * @param object 对象
     * @return 结果
     * @throws IllegalAccessException 异常
     */
    public static Object trimExe(Object object) throws IllegalAccessException {
        if (Objects.isNull(object)) {
            return object;
        }
        Class<? extends Object> clazz = object.getClass();
        // 为非String Char包装类型，不做处理
        if (WRAPPER.contains(clazz)) {
            return object;
        }
        if (STRING_PER.contains(clazz)) {
            return object.toString().trim();
        }
        // 字段为private时
        Field[] fields = clazz.getDeclaredFields();
        if (Objects.isNull(fields) || fields.length == 0) {
            return object;
        }
        for (Field field : fields) {
            field.setAccessible(true);
            Object filedValue = field.get(object);
            log.error(filedValue.toString());
            if (Objects.nonNull(filedValue)) {
                Class<?> aClass = filedValue.getClass();
                if (filedValue instanceof Collection) {
                    trimList(object, field);
                } else if (aClass.isArray()) {
                    trimArray(object, field); //数组中含对象时有bug
                } else if (filedValue instanceof Map) {
                    trimMap(object, field);
                } else if (filedValue instanceof String || filedValue instanceof Character) {
                    trimStringFiled(object, field);
                } else if (WRAPPER.contains(aClass)) {
                    continue;
                } else {
                    trimObjectFiled(object, field);
                }
            }
        }

        return object;
    }

    /**
     * 处理字符串类型的数据
     *
     * @param object
     * @param field
     * @return
     * @throws IllegalAccessException
     */
    public static Object trimStringFiled(Object object, Field field) throws IllegalAccessException {
        Class<?> filedType = field.getType();
        if (filedType == String.class || filedType == Character.class) {
            field.setAccessible(true);
            if (Objects.nonNull(field.get(object))) {
                // 在原有的对象上设置去除首尾空格的新值
                field.set(object, String.valueOf(field.get(object)).trim());
            }
        }
        return object;
    }

    /**
     * 处理实体对象类型的数据
     *
     * @param object
     * @param field1
     * @return
     * @throws IllegalAccessException
     */
    public static Object trimObjectFiled(Object object, Field field1) throws IllegalAccessException {
        Object filedValue1 = field1.get(object);
        Class<?> filedType1 = field1.getType();
        if (Objects.isNull(field1)) {
            return field1;
        }
        // 为非String Char包装类型，不做处理
        if (WRAPPER.contains(filedType1)) {
            return object;
        }
        if (filedType1 == String.class || filedType1 == Character.class) {
            return filedValue1.toString().trim();
        }
        // 字段为private时，无法修改字段值，需要反射
        Field[] fields = filedType1.getDeclaredFields();
        if (Objects.isNull(fields) || fields.length == 0) {
            return object;
        }
        for (Field field : fields) {
            field.setAccessible(true);
            Object filedValue = field.get(filedValue1);
            if (Objects.nonNull(filedValue)) {
                Class<?> aClass = filedValue.getClass();
                if (filedValue instanceof List) {
                    trimList(filedValue1, field);
                } else if (aClass.isArray()) { //判断这个对象是否是数组
                    trimArray(filedValue1, field);
                } else if (filedValue instanceof Map) {
                    trimMap(filedValue1, field);
                } else if (filedValue instanceof String || filedValue instanceof Character) {
                    trimStringFiled(filedValue1, field);
                } else if (WRAPPER.contains(aClass)) {
                    continue;
                } else {
                    trimObjectFiled(filedValue1, field);
                }
            }
            Class<?> filedType = field.getType();
            // 只处理字符串类型
            if (filedType != String.class) {
                continue;
            }
            if (filedType == String.class || filedType == Character.class) {
                // 去除private权限，变为可更改
                field.setAccessible(true);
                if (Objects.nonNull(field.get(filedValue1))) {
                    // 在原有的对象上设置去除首尾空格的新值
                    field.set(filedValue1, String.valueOf(field.get(filedValue1)).trim());
                }
            }
        }
        return object;
    }

    /**
     * 处理Collection类型的数据
     *
     * @param object
     * @param field
     * @return
     * @throws IllegalAccessException
     */
    public static Object trimList(Object object, Field field) throws IllegalAccessException {
        Object filedValue = field.get(object);
        Object[] objs = ((Collection) filedValue).toArray();
        if (objs == null) {
            return object;
        }
        if (objs.length == 0) {
            return object;
        }
        List<String> list = new ArrayList<>();
        for (int index = 0; index < objs.length; index++) {
            Object e1 = objs[index];
            if (e1.getClass() == String.class) {
                String trim = String.valueOf(e1).trim();
                list.add(trim);
            } else {
                trimExe(e1);
            }
        }
        if (CollectionUtils.isNotEmpty(list)) {
            field.set(object, list);
        }
        return object;
    }

    /**
     * 处理MAP类型的数据
     *
     * @param object
     * @param field
     * @return
     * @throws IllegalAccessException
     */
    public static Object trimMap(Object object, Field field) throws IllegalAccessException {
        Object filedValue = field.get(object);
        Map<Object, Object> filedValueMap = (Map<Object, Object>) filedValue;
        if (org.springframework.util.CollectionUtils.isEmpty(filedValueMap)) {
            return object;
        }
        for (Map.Entry<Object, Object> entry : filedValueMap.entrySet()) {
            Object entryValue = entry.getValue();
            Class<?> aClass1 = entryValue.getClass();
            if (aClass1 == String.class) {
                String trim = String.valueOf(entryValue).trim();
                entry.setValue(trim);
            } else {
                trimExe(entryValue);
            }
        }
        if (!org.springframework.util.CollectionUtils.isEmpty(filedValueMap)) {
            field.set(object, filedValueMap);
        }
        return object;
    }

    /**
     * 处理数组类型的数据
     *
     * @param object
     * @param field
     * @return
     * @throws IllegalAccessException
     */
    public static Object trimArray(Object object, Field field) throws IllegalAccessException {
        Object filedValue = field.get(object);
        Object[] objs = (Object[]) filedValue;
        if (objs == null) {
            return object;
        }
        if (objs.length == 0) {
            return object;
        }
        List<String> list = new ArrayList<>();
        for (int index = 0; index < objs.length; index++) {
            Object e1 = objs[index];
            if (e1.getClass() == String.class) {
                String trim = String.valueOf(e1).trim();
                list.add(trim);
                objs[index] = trim;
            } else {
                trimExe(e1);
            }
        }
        if (objs != null && objs.length > 0) {
            field.set(object, objs);
        }
        return object;
    }
}