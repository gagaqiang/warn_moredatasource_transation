package com.utils;

import org.springframework.util.Assert;

import java.lang.reflect.Method;

/**
 * Created by yihao on 2019/5/13.
 */
public class ReflectUtil {

    /**
     *
     * <li>创建时间： 2013-6-17 下午6:01:41</li>
     * <li>创建人：amos.zhou </li>
     * <li>方法描述 : 寻找方法名唯 一的方法</li>
     * @param clazz
     * @param name
     * @return
     */
    public static Method findUniqueMethod(Class<?> clazz, String name) {
        Assert.notNull(clazz, "Class must not be null");
        Assert.notNull(name, "Method name must not be null");
        Class<?> searchType = clazz;
        while (searchType != null) {
            Method[] methods = (searchType.isInterface() ? searchType.getMethods() : searchType.getDeclaredMethods());
            for (Method method : methods) {
                if (name.equals(method.getName())) {
                    return method;
                }
            }
            searchType = searchType.getSuperclass();
        }
        return null;
    }
}
