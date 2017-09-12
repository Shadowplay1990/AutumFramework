package com.autumnframework.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.*;

/**
 * Created by Administrator on 2017-09-08.
 */
public abstract class ResourceUtils {

    public static final String URL_PROTOCOL_VFS = "vfs";
    public static final String URL_PROTOCOL_FILE = "file";
    public static final String URL_PROTOCOL_JAR = "jar";
    public static final String URL_PROTOCOL_WAR = "war";
    public static final String URL_PROTOCOL_ZIP = "zip";
    public static final String URL_PROTOCOL_WSJAR = "wsjar";
    public static final String URL_PROTOCOL_VFSZIP = "vfszip";
    public static final String URL_PROTOCOL_VFSFILE = "vfsfile";
    public static final String JAR_FILE_EXTENSION = ".jar";
    public static final String JAR_URL_SEPARATOR = "!/";
    public static final String WAR_URL_SEPARATOR = "*/";
    public static final String WAR_URL_PREFIX = "war:";
    public static final String FILE_URL_PREFIX = "file:";

    public static URI toURI(String location) throws URISyntaxException {
        return new URI(StringUtils.replace(location," ","%20"));
    }

    public static URI toURI(URL url) throws URISyntaxException {
        return toURI(url.toString());
    }

    public static File getFile(URL resourceUrl,String description) throws FileNotFoundException{
        Assert.notNull(resourceUrl,"resourceUrl不能为空！");
        if (!resourceUrl.getProtocol().equals(ResourceUtils.URL_PROTOCOL_FILE)){
            throw new FileNotFoundException(description+" 不能解析绝对文件路径，" +
                    "因为它在文件系统中不存在"+resourceUrl);
        }
        try {
            return new File(toURI(resourceUrl).getSchemeSpecificPart());
        }catch (URISyntaxException e){
            return new File(resourceUrl.getFile());
        }
    }

    public static File getFile(URI resourceUri, String description) throws FileNotFoundException {
        Assert.notNull(resourceUri, "Resource URI must not be null");
        if (!URL_PROTOCOL_FILE.equals(resourceUri.getScheme())) {
            throw new FileNotFoundException(
                    description + " cannot be resolved to absolute file path " +
                            "because it does not reside in the file system: " + resourceUri);
        }
        return new File(resourceUri.getSchemeSpecificPart());
    }

    public static boolean isJarURL(URL url){
        String protocol = url.getProtocol();
        return (URL_PROTOCOL_JAR.equals(protocol) || URL_PROTOCOL_WAR.equals(protocol)
        || URL_PROTOCOL_ZIP.equals(protocol) || URL_PROTOCOL_VFSZIP.equals(protocol));
    }

    public static boolean isFileURL(URL url) {
        String protocol = url.getProtocol();
        return (URL_PROTOCOL_FILE.equals(protocol) || URL_PROTOCOL_VFSFILE.equals(protocol) ||
                URL_PROTOCOL_VFS.equals(protocol));
    }

    public static URL extractArchiveURL(URL jarURL) throws MalformedURLException{
        String jarURLFile = jarURL.getFile();

        int endIndex=jarURLFile.indexOf(WAR_URL_SEPARATOR);
        if (endIndex!=-1){
            String warFile=jarURLFile.substring(0,endIndex);
            if (jarURL.getProtocol().equals(URL_PROTOCOL_WAR)){
                return new URL(warFile);
            }

            int startIndex = warFile.indexOf(WAR_URL_PREFIX);
            if (startIndex != -1) {
                return new URL(warFile.substring(startIndex + WAR_URL_PREFIX.length()));
            }
        }

        return extractJarFileURL(jarURL);
    }

    public static URL extractJarFileURL(URL jarUrl) throws MalformedURLException {
        String urlFile = jarUrl.getFile();
        int separatorIndex = urlFile.indexOf(JAR_URL_SEPARATOR);
        if (separatorIndex != -1) {
            String jarFile = urlFile.substring(0, separatorIndex);
            try {
                return new URL(jarFile);
            }
            catch (MalformedURLException ex) {
                // Probably no protocol in original jar URL, like "jar:C:/mypath/myjar.jar".
                // This usually indicates that the jar file resides in the file system.
                if (!jarFile.startsWith("/")) {
                    jarFile = "/" + jarFile;
                }
                return new URL(FILE_URL_PREFIX + jarFile);
            }
        }
        else {
            return jarUrl;
        }
    }

    public static void useCachesIfNecessary(URLConnection con) {
        con.setUseCaches(con.getClass().getSimpleName().startsWith("JNLP"));
    }
}





















