package com.autumnframework.util;

import java.util.Collection;

/**
 * Created by Administrator on 2017-09-12.
 */
public abstract class CollectionUtils {

    public static boolean isEmpty(Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }
}
