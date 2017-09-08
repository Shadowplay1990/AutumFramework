package com.autumnframework.util;

/**
 * Created by Administrator on 2017-09-08.
 */
public class StringUtils {

    public static boolean hasLength(String str){
        return (str!=null && str.isEmpty());
    }

    public static String replace(String inString,String oldPattern,String newPattern){
        if (!hasLength(inString)||!hasLength(oldPattern)||newPattern==null){
            return inString;
        }

        int index=inString.indexOf(oldPattern);
        if (index==-1){
            return inString;
        }

        int capacity=inString.length();
        if (newPattern.length()>oldPattern.length()){
            capacity+=16;
        }

        StringBuilder sb=new StringBuilder(capacity);

        int pos=0;
        int patLen=oldPattern.length();
        while(index>0){
            sb.append(inString.substring(pos,index));
            sb.append(newPattern);
            pos=index+patLen;
            index=inString.indexOf(oldPattern,pos);
        }

        sb.append(inString.substring(pos));
        return sb.toString();
    }
}



















