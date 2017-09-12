package com.autumnframework.core.io;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * 该接口由底层资源的具体实现（诸如file，class path）抽象而来，用于描述资源。
 * @author Tang Jiujia
 * @since 2017/9/12
 */
public interface Resource extends InputStreamSource{

    /**
    * 判断一个资源是否真实存在。
    */
    boolean exists();

    /**
    * 通过{@link #getInputStream()}判断一个资源是否为可读。
    */
    default boolean isReadable(){return true;}

    /**
    * 判断输入流是否为打开状态，如果是打开状态，则该输入流不能
    * 多次读取，必须关闭以避免内存泄漏。默认为{@code false}
    */
    default boolean isOpen(){return false;}

    /**
    * 判断该资源是否是文件系统中的文件
    * 注意该方法返回true并不一定保证
    * {@link #getFile()}能调用成功。
    */
    default boolean isFile(){return false;}

    /**
    * 返回该资源的URL
    */
    URL getURL() throws IOException;

    /**
    * 获得该资源的URI
    */
    URI getURI() throws IOException;

    /**
    * 返回该资源的File
    */
    File getFile() throws IOException;

    /**
    * 返回不能为空
    */
    default ReadableByteChannel readableChannel() throws IOException{
        return Channels.newChannel(getInputStream());
    }

    /**
     * 返回该资源的长度
     */
    long contentLength() throws IOException;

    /**
     * 返回该资源的最近修改时间
     */
    long lastModified() throws IOException;

    /**
     * 创建该资源的相对资源
     */
    Resource createRelative(String relativePath) throws IOException;

    /**
     * 获得该资源的文件名，即该资源路径的最后一部分内容。
     */
    String getFilename();

    /**
     * 获得该资源的描述，主要用于错误输出
     */
    String getDescription();
}
























