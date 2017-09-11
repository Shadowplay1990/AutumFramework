package com.autumnframework.util;

/**
 * Created by Administrator on 2017-09-11.
 */
public class Assert {

    public static void isNull(Object object,String message){
        if (object!=null){
            throw new IllegalArgumentException(message);
        }
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
