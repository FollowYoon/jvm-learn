package com.jvm.nettygateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpResponse;

/**
 * @author qindong
 * @date 2021/9/29 15:22
 */
public interface HttpResponseFilter {

    void filter(FullHttpResponse response, ChannelHandlerContext ctx);

}
