package com.jvm.nettygateway.inbound;

import com.jvm.nettygateway.filter.HttpRequestFilter;
import com.jvm.nettygateway.filter.HttpRequestHeaderFilter;
import com.jvm.nettygateway.outbound.HttpOutboundHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;

/**
 * @author qindong
 * @date 2021/9/28 15:06
 */
public class HttpInboundHandler extends ChannelInboundHandlerAdapter {

    private String proxyServer;

    private HttpOutboundHandler outboundHandler;

    private HttpRequestFilter filter = new HttpRequestHeaderFilter();

    public HttpInboundHandler(String proxyServer) {
        this.proxyServer = proxyServer;
        this.outboundHandler = new HttpOutboundHandler(proxyServer);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            FullHttpRequest httpRequest = (FullHttpRequest) msg;
            outboundHandler.handle(httpRequest, ctx, filter);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }


}
