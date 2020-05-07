package com.zzw.remoting.netty;

import com.zzw.common.RPCException;
import com.zzw.remoting.Server;
import com.zzw.remoting.Terminal;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;

import java.net.InetSocketAddress;
import java.util.Collection;
import java.util.Map;

/**
 * @Date 2020/5/4
 * @Author zhenwei.wang
 */
public class NettyServer implements Server, Terminal {
    Map<String/* ip:port */, Channel> channelMap;

    ServerBootstrap serverBootstrap;

    Channel channel;

    @Override
    public Channel getChannel(InetSocketAddress socketAddress) {
        return null;
    }

    @Override
    public Collection<Channel> getAllChannels() {
        return null;
    }

    @Override
    public void start() {

    }

    @Override
    public InetSocketAddress getLocalAddress() {
        return null;
    }

    @Override
    public void send(Object msg) throws RPCException {

    }

    @Override
    public void close() {

    }

    @Override
    public boolean isClosed() {
        return false;
    }
}
