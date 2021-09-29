package com.jvm.nettygateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpResponse;

/**
 * @author qindong
 * @date 2021/9/29 15:23
 */
public class HttpResponseHeaderFilter implements HttpResponseFilter {

    @Override
    public void filter(FullHttpResponse response, ChannelHandlerContext ctx) {
        System.out.println("进入http response过滤器...");
        response.headers().set("rsp", "hello nio1");
    }

}
