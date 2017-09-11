package com.autumnframework.core.io;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;

/**
 * Created by Administrator on 2017-09-11.
 */
public interface WritableResource extends Resource{

    default boolean isWritable(){return true;}

    OutputStream getOutputStream() throws IOException;

    default WritableByteChannel wirtableChannel() throws IOException{
        return Channels.newChannel(getOutputStream());
    }
}
