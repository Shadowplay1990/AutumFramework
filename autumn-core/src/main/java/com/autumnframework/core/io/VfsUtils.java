package com.autumnframework.core.io;

import com.autumnframework.util.ReflictionUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;

/**
 * Created by Administrator on 2017-09-11.
 */
public abstract class VfsUtils {

    private static final String VFS3_PKG = "org.jboss.vfs.";
    private static final String VFS_NAME = "VFS";

    private static Method VFS_METHOD_GET_ROOT_URL;
    private static Method VFS_METHOD_GET_ROOT_URI;

    static{
        ClassLoader loader = VfsUtils.class.getClassLoader();
        try {
            Class<?> vfsClass = loader.loadClass(VFS3_PKG + VFS_NAME);

            VFS_METHOD_GET_ROOT_URL = vfsClass.getMethod("getChild", URL.class);
            VFS_METHOD_GET_ROOT_URI = vfsClass.getMethod("getChild", URI.class);
        }catch (Exception ex){
            throw new IllegalStateException("无法找到Jboss VFS相关API！");
        }
    }

    protected static Object invokeVfsMethod(Method method,Object target,Object... args) throws IOException{
        try {
            method.invoke(target,args);
        }
        catch (InvocationTargetException e){
            Throwable targetEx = e.getTargetException();
            if (targetEx instanceof IOException) throw (IOException) targetEx;
            ReflictionUtils.handleInvocationTargetException(e);
        }
        catch (Exception ex){
            ReflictionUtils.handleReflectionException(ex);
        }

        throw new IllegalStateException("到达的代码路径无效。");
    }

    static Object getRoot(URI uri) throws IOException {
        return invokeVfsMethod(VFS_METHOD_GET_ROOT_URI,null,uri);
    }

    protected static Object getRoot(URL url) throws IOException {
        return invokeVfsMethod(VFS_METHOD_GET_ROOT_URL,null,url);
    }
}
