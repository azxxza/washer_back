package com.seawaterbt.ssm.core.utils;


import java.util.Arrays;

/**
 * 异常工具类
 *
 * @author azx
 * @date 2019/4/10 15:17
 **/

public class ExceptionUtils {

    /**
     * 获取异常栈信息
     */
    public static String getExpStackTraceDetail(Exception e) {
        StackTraceElement[] stackTrace = e.getStackTrace();
        String strStack = Arrays.toString(stackTrace);
        return Arrays.toString(stackTrace).substring(1, strStack.length() - 1).replace(",", ",<br/>");
    }


    public static String getExpStackTraceSimple(Exception ex) throws RuntimeException {
        try {
            StackTraceElement[] st = ex.getStackTrace();
            StringBuilder exceptionMessage = new StringBuilder("");
            for (StackTraceElement stackTraceElement : st) {
                String exclass = stackTraceElement.getClassName();
                String method = stackTraceElement.getMethodName();
            }
            return exceptionMessage.toString();
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
