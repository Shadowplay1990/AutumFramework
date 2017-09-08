package com.autumnframework.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by Administrator on 2017-09-08.
 */
public abstract class ResourceUtils {

    public static URI toURI(String location) throws URISyntaxException {
        return new URI(StringUtils.replace(location," ","%20"));
    }

    public static URI toURI(URL url) throws URISyntaxException {
        return toURI(url.toString());
    }
}
