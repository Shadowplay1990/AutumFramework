package com.autumnframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 该接口用于source对象获取InputStream。
 * <p>框架内的Resource接口扩展于该接口。
 * <p>该接口的实现类允许读取底层stream多次。
 * @author Tang Jiujia
 * @since 2017/9/12
 */
public interface InputStreamSource {

    /**
    * 每次调用该方法返回一个新的stream。
    * @return 返回的输入流不能为空
    * @throws java.io.FileNotFoundException，如果底层资源不存在抛出该异常
    */
    InputStream getInputStream() throws IOException;

}
