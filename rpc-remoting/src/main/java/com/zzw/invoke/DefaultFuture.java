package com.zzw.invoke;

import io.netty.channel.Channel;

/**
 * @Date 2020/5/4
 * @Author zhenwei.wang
 */
public class DefaultFuture implements Future {

    private Long requestId;

    private Channel channel;
    private Callback callback;


    public DefaultFuture(Long requestId, Channel channel) {
        this.requestId = requestId;
        this.channel = channel;
    }

    public Long getRequestId() {
        return requestId;
    }

    public Channel getChannel() {
        return channel;
    }

    @Override
    public void setCallback(Callback callback) {
        this.callback = callback;
    }
}
