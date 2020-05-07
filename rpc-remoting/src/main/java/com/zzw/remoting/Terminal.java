package com.zzw.remoting;

import com.zzw.common.RPCException;

import java.net.InetSocketAddress;

/**
 * @Date 2020/5/4
 * @Author zhenwei.wang
 */
public interface Terminal {

    /**
     * socket地址
     * @return
     */
    InetSocketAddress getLocalAddress();

    /**
     * send msg
     * @param msg 通信内容
     * @throws RPCException
     */
    void send(Object msg) throws RPCException;

    /**
     * close terminal，client/server
     */
    void close();

    /**
     * client/server is closed or not.
     * @return
     */
    boolean isClosed();
}
