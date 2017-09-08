package com.autumnframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 该接口用于source对象获取InputStream。
 * <p>框架内的Resource接口扩展于该接口。
 * <p>
 * Created by Administrator on 2017-09-07.
 */
public interface InputStreamSource {

    InputStream getInputStream() throws IOException;
}
