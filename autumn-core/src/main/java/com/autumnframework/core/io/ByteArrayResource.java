package com.autumnframework.core.io;

import com.autumnframework.util.Assert;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * Created by Administrator on 2017-09-11.
 */
public class ByteArrayResource extends AbstractResource{

    private final byte[] byteArray;
    private final String description;

    public ByteArrayResource(byte[] byteArray,String description){
        Assert.notNull(byteArray,"ByteArray不能为空。");
        this.byteArray=byteArray;
        this.description=(description!=null?description:"");
    }

    public ByteArrayResource(byte[] byteArray){
        this(byteArray,"从byte array加载的资源！");
    }

    public final byte[] getByteArray(){
        return this.byteArray;
    }

    @Override
    public boolean exists(){return true;}

    @Override
    public long contentLength(){return this.byteArray.length;}

    @Override
    public InputStream getInputStream() throws IOException {
        return new ByteArrayInputStream(byteArray);
    }

    @Override
    public String getDescription() {
        return "Byte Array resource [ "+this.description+" ]";
    }

    @Override
    public boolean equals(Object obj) {
        return (this==obj || (obj instanceof ByteArrayResource &&
                Arrays.equals(((ByteArrayResource)obj).byteArray,this.byteArray)));
    }

    @Override
    public int hashCode() {
        return (byte[].class.hashCode() * 29 * this.byteArray.length);
    }
}

