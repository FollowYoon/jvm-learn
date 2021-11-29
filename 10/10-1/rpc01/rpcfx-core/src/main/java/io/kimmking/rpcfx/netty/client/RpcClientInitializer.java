package io.kimmking.rpcfx.netty.client;

import io.kimmking.rpcfx.netty.RpcDecoder;
import io.kimmking.rpcfx.netty.RpcEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author qindong
 * @date 2021/11/17 11:54
 */
public class RpcClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline p = socketChannel.pipeline();
        p.addLast("decoder", new RpcDecoder());
        p.addLast("encoder", new RpcEncoder());
        p.addLast("clientHandler", new RpcClientSyncHandler());
    }
}
