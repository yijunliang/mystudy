package com.yjl.excel;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
/**
 * 把bean转化为Map
 * @author Administrator
 *
 * @param <T>
 */
public class BeanToMap<T> {

    public String getMethodName(String fieldName){
        byte[] buffer = fieldName.getBytes();
        buffer[0] = (byte)(buffer[0]-32);
        String name = new String(buffer);
        return "get"+name;
    }

    public Map<String,Object> getMap(T entity){
        Field[] fields = entity.getClass().getDeclaredFields();
        Map<String,Object> map = new HashMap<String,Object>();
        for (int j = 0; j < fields.length; j++) {
            try {
                Method method = entity.getClass().getMethod(getMethodName(fields[j].getName()));
                map.put(fields[j].getName(),method.invoke(entity));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }


}
