package com.autumnframework.core.io;

import com.autumnframework.core.NestedIOException;
import com.autumnframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Created by Administrator on 2017-09-08.
 */
public abstract class AbstractResource implements Resource{

    @Override
    public boolean exists() {
        try {
            return getFile().exists();
        } catch (IOException e){
            try {
                InputStream is=getInputStream();
                is.close();
                return true;
            }catch (Throwable ex){
                return false;
            }
        }
    }

    @Override
    public URL getURL() throws IOException {
        throw new FileNotFoundException(getDescription()+"不能解析为URL。");
    }

    @Override
    public URI getURI() throws IOException {
        URL url=getURL();
        try {
            return ResourceUtils.toURI(url);
        } catch (URISyntaxException e) {
            throw new NestedIOException("无效的URI ["+url.toString()+"]",e);
        }
    }

    @Override
    public File getFile() throws IOException {
        throw new FileNotFoundException(getDescription()+"无法解析为绝对文件路径。");
    }

    @Override
    public long contentLength() throws IOException {
        InputStream is=getInputStream();
        try{
            long size=0;
            byte[] buf=new byte[255];
            int read;
            while ((read=is.read(buf))!=-1){
                size+=read;
            }
            return size;
        }finally {
            try{
                is.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public long lastModified() throws IOException {
        long lastModified=getFileForLastModifiedCheck().lastModified();
        if (lastModified==0L){
            throw new FileNotFoundException(getDescription()+"在文件查询最后修改时间时无法解析文件。");
        }
        return lastModified;
    }

    protected File getFileForLastModifiedCheck() throws IOException {
        return getFile();
    }

    @Override
    public Resource createRelative(String relativePath) throws IOException {
        throw new FileNotFoundException("不能为"+getDescription()+" 创建相对资源。");
    }

    @Override
    public String getFilename() {
        return null;
    }

    @Override
    public String getDescription() {
        return getDescription();
    }

    @Override
    public boolean isReadable() {
        return true;
    }

    @Override
    public boolean isOpen() {
        return false;
    }

    @Override
    public boolean isFile() {
        return false;
    }

    @Override
    public ReadableByteChannel readableChannel() throws IOException {
        return Channels.newChannel(getInputStream());
    }

    @Override
    public boolean equals(Object obj){
        return (obj == this ||
                (obj instanceof Resource && ((Resource) obj).
                        getDescription().equals(getDescription())));
    }

    @Override
    public int hashCode(){
        return getDescription().hashCode();
    }
}
