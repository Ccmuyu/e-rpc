package com.zzw.remoting;

/**
 * @Author zhenwei.wang
 */
public interface Client {

    /**
     * connect remote server
     */
    void connect();

    /**
     * connect status
     *
     * @return
     */
    boolean isConnected();
}
