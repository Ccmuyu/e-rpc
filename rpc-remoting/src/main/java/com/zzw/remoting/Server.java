package com.zzw.remoting;

import io.netty.channel.Channel;

import java.net.InetSocketAddress;
import java.util.Collection;

/**
 * @Date 2020/5/4
 * @Author zhenwei.wang
 */
public interface Server {

    Channel getChannel(InetSocketAddress socketAddress);


    Collection<Channel> getAllChannels();

    void start();

}
