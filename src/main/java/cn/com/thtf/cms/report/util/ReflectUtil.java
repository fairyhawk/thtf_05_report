/**
 * ClassName  ReflectUtil
 *
 * History
 * Create User: Shiy
 * Create Date: 2010-8-24
 * Update User:
 * Update Date:
 */
package cn.com.thtf.cms.report.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 通过反射赋值
 * 
 * @author Shiy
 */

public class ReflectUtil {

    private static Logger log = LoggerFactory.getLogger(ReflectUtil.class);

    public static void setFieldValue(Object target, String fname, Class<?> ftype,
            Object fvalue) {
        if (target == null || fname == null || "".equals(fname)
                || (fvalue != null && !ftype.isAssignableFrom(fvalue.getClass()))) {
            return;
        }
        Class<? extends Object> clazz = target.getClass();
        try {
            Method method = clazz.getDeclaredMethod(
                    "set" + Character.toUpperCase(fname.charAt(0)) + fname.substring(1),
                    ftype);
            if (!Modifier.isPublic(method.getModifiers())) {
                method.setAccessible(true);
            }
            method.invoke(target, fvalue);

        } catch (Exception me) {
            log.warn("没有set方法，改为field方式赋值");
            try {
                Field field = clazz.getDeclaredField(fname);
                if (!Modifier.isPublic(field.getModifiers())) {
                    field.setAccessible(true);
                }
                field.set(target, fvalue);
            } catch (Exception fe) {
                log.error("", fe);
            }
        }
    }
}
