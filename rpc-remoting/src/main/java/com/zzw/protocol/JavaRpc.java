package com.zzw.protocol;

import com.zzw.util.JsonUtil;

/**
 * @Date 2020/5/4
 * @Author zhenwei.wang
 */
public class JavaRpc implements Protocol {

    private Long requestId;
    //class+method
    private String invokeMethod;

    private Object[] parameters;

    @Override
    public Long getRequestId() {
        return requestId;
    }

    @Override
    public String invokeMethod() {
        return invokeMethod;
    }

    @Override
    public Object[] getParameters() {
        return parameters;
    }

    @Override
    public String getProtocol() {
        return toString();
    }

    @Override
    public String version() {
        return null;
    }

    @Override
    public String protocolName() {
        return null;
    }

    @Override
    public String toString() {
        return "requestId=" + requestId +
                "&invokeMethod='" + invokeMethod + '\'' +
                "&parameters=" + JsonUtil.jsons(parameters);
    }
}
