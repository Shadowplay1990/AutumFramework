package com.autumnframework.core.io;

import com.autumnframework.util.ReflictionUtils;
import com.autumnframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

/**
 * Created by Administrator on 2017-09-11.
 */
public abstract class AbstractFileResolvingResource extends AbstractResource{

    @Override
    public File getFile() throws IOException {
        return super.getFile();
    }

    @Override
    protected File getFileForLastModifiedCheck() throws IOException {
        return super.getFileForLastModifiedCheck();
    }

    @Override
    public boolean isFile() {
        try {
            URL url = getURL();
            if (url.getProtocol().startsWith(ResourceUtils.URL_PROTOCOL_VFS)){
                return VfsResourceDelegate.getResource(url).isFile();
            }
            return ResourceUtils.URL_PROTOCOL_FILE.equals(url.getProtocol());
        }catch (IOException e){
            return false;
        }
    }

    private static class VfsResourceDelegate{

        public static Resource getResource(URL url) throws IOException {
            return new VfsResource(VfsUtils.getRoot(url));
        }

        public static Resource getResource(URI uri) throws IOException {
            return new VfsResource(VfsUtils.getRoot(uri));
        }
    }
}

























