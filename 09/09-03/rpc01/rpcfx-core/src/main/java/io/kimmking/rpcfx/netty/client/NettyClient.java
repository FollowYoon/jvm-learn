package io.kimmking.rpcfx.netty.client;

import com.alibaba.fastjson.JSON;
import io.kimmking.rpcfx.api.RpcfxNettyDTO;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;

/**
 * @author qindong
 * @date 2021/11/17 11:50
 */
public class NettyClient {

    private NettyClient(){}

    private static class NettyClientInstance{
        private static final NettyClient INSTANCE = new NettyClient();
    }

    public static NettyClient getInstance(){
        return NettyClientInstance.INSTANCE;
    }

    public RpcfxResponse getResponse(RpcfxRequest request, String url) throws URISyntaxException, InterruptedException {
        RpcfxNettyDTO rpcfxNettyDTO = new RpcfxNettyDTO();
        String requestJson = JSON.toJSONString(request);
        rpcfxNettyDTO.setLength(requestJson.getBytes(CharsetUtil.UTF_8).length);
        rpcfxNettyDTO.setContent(requestJson.getBytes(CharsetUtil.UTF_8));

        RpcClientSyncHandler handler = new RpcClientSyncHandler();
        handler.setLatch(new CountDownLatch(1));
        URI uri = new URI(url);
        Channel channel = createChannel(uri.getHost(), uri.getPort());
        channel.pipeline().replace("clientHandler", "clientHandler", handler);
        channel.writeAndFlush(rpcfxNettyDTO).sync();
        return handler.getResponse();
    }

    private Channel createChannel(String ip, int port) throws InterruptedException {
        EventLoopGroup clientGroup = new NioEventLoopGroup(1);
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(clientGroup)
                .option(ChannelOption.SO_REUSEADDR, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.AUTO_CLOSE, true)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .channel(NioSocketChannel.class)
                .handler(new RpcClientInitializer());
        return bootstrap.connect(ip, port).sync().channel();
    }

}
