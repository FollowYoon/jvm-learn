package com.jvm.nettygateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @author qindong
 * @date 2021/9/29 15:19
 */
public interface HttpRequestFilter {

    void filter(FullHttpRequest request, ChannelHandlerContext ctx);

}
