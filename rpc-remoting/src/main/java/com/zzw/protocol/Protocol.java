package com.zzw.protocol;

/**
 * @Date 2020/5/4
 * @Author zhenwei.wang
 */
public interface Protocol {

    /**
     * request unique id
     *
     * @return
     */
    Long getRequestId();

    /**
     * remote rpc method
     *
     * @return
     */
    String invokeMethod();

    /**
     * rpc parameters
     *
     * @return
     */
    Object[] getParameters();

    /**
     * protocol string
     *
     * @return
     */
    String getProtocol();

    /**
     * protocol version
     *
     * @return
     */
    String version();

    /**
     * protocol name
     *
     * @return
     */
    String protocolName();
}
