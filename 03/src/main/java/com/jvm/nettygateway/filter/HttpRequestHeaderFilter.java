package com.jvm.nettygateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author qindong
 * @date 2021/9/29 15:20
 */
public class HttpRequestHeaderFilter implements HttpRequestFilter{
    @Override
    public void filter(FullHttpRequest request, ChannelHandlerContext ctx) {
        System.out.println("进入http request过滤器...");
        request.headers().set("req", "netty gateway");
    }
}
