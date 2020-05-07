package com.zzw.common;

/**
 * @Date 2020/5/4
 * @Author zhenwei.wang
 */
public class RPCException extends RuntimeException {

    public RPCException(String message) {
        super(message);
    }

    public RPCException(String message, Throwable cause) {
        super(message, cause);
    }

    public RPCException(Throwable cause) {
        super(cause);
    }

}
