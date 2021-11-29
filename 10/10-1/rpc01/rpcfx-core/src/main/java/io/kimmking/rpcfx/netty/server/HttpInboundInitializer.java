package io.kimmking.rpcfx.netty.server;

import io.kimmking.rpcfx.netty.RpcDecoder;
import io.kimmking.rpcfx.netty.RpcEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;


/**
 * @author qindong
 * @date 2021/9/28 15:04
 */
public class HttpInboundInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel){
        ChannelPipeline p = socketChannel.pipeline();
        p.addLast("decoder", new RpcDecoder());
        p.addLast("encoder", new RpcEncoder());
        p.addLast(new HttpInboundHandler());
    }
}
