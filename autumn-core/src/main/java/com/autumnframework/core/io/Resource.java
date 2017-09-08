package com.autumnframework.core.io;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Created by Administrator on 2017-09-07.
 */
public interface Resource extends InputStreamSource{

    boolean exists();

    default boolean isReadable(){return true;}

    default boolean isOpen(){return false;}

    default boolean isFile(){return false;}

    URL getURL() throws IOException;

    URI getURI() throws IOException;

    File getFile() throws IOException;

    default ReadableByteChannel readableChannel() throws IOException{
        return Channels.newChannel(getInputStream());
    }

    long contentLength() throws IOException;

    long lastModified() throws IOException;

    Resource createRelative() throws IOException;

    String getFilename();

    String getDescription();
}























