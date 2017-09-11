package com.autumnframework.core.io;

import com.autumnframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017-09-11.
 */
public class VfsResource extends AbstractResource{

    private final Object resource;

    public VfsResource(Object resource) {
        Assert.notNull(resource,"虚拟文件不能为null");
        this.resource = resource;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return null;
    }
}
