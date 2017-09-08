package com.autumnframework.core;

import java.io.IOException;

/**
 * Created by Administrator on 2017-09-08.
 */
public class NestedIOException extends IOException{

    static {
        NestedIOException.class.getName();
    }

    public NestedIOException(String msg){
        super(msg);
    }

    public NestedIOException(String msg,Throwable ex){
        super(msg,ex);
    }
}
