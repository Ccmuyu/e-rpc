package com.zzw.remoting.netty;

import com.zzw.invoke.InvokeHandler;
import com.zzw.protocol.JavaRpc;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Date 2020/5/4
 * @Author zhenwei.wang
 */
public class SimpleInBoundHandler extends SimpleChannelInboundHandler<JavaRpc> {

    private transient Channel channel;

    private InvokeHandler invokeHandler;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JavaRpc msg) throws Exception {
        invokeHandler = new InvokeHandler(ctx.channel(), msg);
        invokeHandler.invoke(msg);
    }


}
