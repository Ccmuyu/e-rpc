package com.zzw.invoke;

import com.zzw.protocol.Protocol;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Date 2020/5/4
 * @Author zhenwei.wang
 */
public class InvokeHandler implements Invokable<Protocol, Callback> {

    Map<Long, Callback> responseHandlers = new ConcurrentHashMap<>(1 << 6);

    Channel channel;
    Protocol protocol;

    public InvokeHandler(Channel channel, Protocol protocol) {
        this.protocol = protocol;
        this.channel = channel;
    }

    @Override
    public Callback invoke(Protocol protocol) {
        Long requestId = protocol.getRequestId();
        Callback callback = responseHandlers.get(requestId);
        responseHandlers.remove(requestId);
        return callback;
    }

}
