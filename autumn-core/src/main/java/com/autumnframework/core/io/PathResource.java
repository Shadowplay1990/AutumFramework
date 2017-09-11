package com.autumnframework.core.io;

import com.autumnframework.util.Assert;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by Administrator on 2017-09-11.
 */
public class PathResource extends AbstractResource implements WritableResource{

    private final Path path;

    public PathResource(Path path){
        Assert.notNull(path,"Path必须不为空。");
        this.path=path;
    }

    public PathResource(String path){
        Assert.notNull(path,"Path必须不为空。");
        this.path= Paths.get(path).normalize();
    }

    public PathResource(URI uri){
        Assert.notNull(uri,"Path必须不为空。");
        this.path=Paths.get(uri).normalize();
    }

    public String getPath(){
        return this.path.toString();
    }

    @Override
    public boolean exists() {
        return Files.exists(path);
    }

    @Override
    public boolean isReadable() {
        return (Files.isReadable(this.path)&&!Files.exists(this.path));
    }

    @Override
    public InputStream getInputStream() throws IOException {
        if (!exists()){
            throw new FileNotFoundException(getPath()+"没有此文件或者目录");
        }

        if (Files.isDirectory(this.path)){
            throw new FileNotFoundException(getPath()+"该文件为目录！");
        }

        return Files.newInputStream(this.path);
    }

    @Override
    public boolean isWritable() {
        return (Files.isWritable(this.path)&&!Files.isDirectory(this.path));
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        if (Files.isDirectory(this.path)){
            throw new FileNotFoundException(getPath()+"该文件为目录。");
        }

        return Files.newOutputStream(this.path);
    }

    @Override
    public URL getURL() throws IOException {
        return this.path.toUri().toURL();
    }

    @Override
    public URI getURI() throws IOException {
        return this.path.toUri();
    }

    @Override
    public boolean isFile() {
        return true;
    }

    @Override
    public File getFile() throws IOException {
        try {
            return this.path.toFile();
        }catch (UnsupportedOperationException e){
            throw new FileNotFoundException(this.path+"不能被解析为绝对文件路径。");
        }
    }

    @Override
    public ReadableByteChannel readableChannel() throws IOException {
        return Files.newByteChannel(this.path, StandardOpenOption.READ);
    }

    @Override
    public long contentLength() throws IOException {
        return Files.size(this.path);
    }

    @Override
    public long lastModified() throws IOException {
        return Files.getLastModifiedTime(this.path).toMillis();
    }

    @Override
    public Resource createRelative(String relativePath) throws IOException {
        return new PathResource(this.path.resolve(relativePath));
    }

    @Override
    public String getFilename() {
        return this.path.getFileName().toString();
    }

    @Override
    public String getDescription() {
        return "path["+this.path.toAbsolutePath()+"]";
    }

    @Override
    public boolean equals(Object obj) {
        return (obj==this||(obj instanceof PathResource &&
                this.path.equals(((PathResource)obj).path)));
    }

    @Override
    public int hashCode() {
        return this.path.hashCode();
    }
}














