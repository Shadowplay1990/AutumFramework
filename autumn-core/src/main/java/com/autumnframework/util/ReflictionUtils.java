package com.autumnframework.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * Created by Administrator on 2017-09-11.
 */
public abstract class ReflictionUtils {

    public static void handleInvocationTargetException(InvocationTargetException ex){
        rethrowRuntimeException(ex.getTargetException());
    }

    public static void rethrowRuntimeException(Throwable ex){
        if (ex instanceof RuntimeException) throw (RuntimeException)ex;
        if (ex instanceof Error) throw (Error)ex;
        throw new UndeclaredThrowableException(ex);
    }

    public static void handleReflectionException(Exception e){
        if (e instanceof NoSuchMethodException)
            throw new IllegalStateException("没有找到该方法："+e.getMessage());

        if (e instanceof IllegalAccessException)
            throw new IllegalStateException("该方法不被允许访问"+e.getMessage());

        if (e instanceof InvocationTargetException)
            handleInvocationTargetException((InvocationTargetException) e);

        if (e instanceof RuntimeException)
            throw (RuntimeException)e;

        throw new UndeclaredThrowableException(e);
    }
}




















