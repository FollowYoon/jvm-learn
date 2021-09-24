package com.jvm.learn.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.ReferenceCountUtil;

import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;

/**
 * @author qindong
 * @date 2021/9/24 15:28
 */
public class HttpHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try{
            FullHttpRequest httpRequest = (FullHttpRequest) msg;
            String uri = httpRequest.uri();
            if(uri.contains("/test")){
                handlerTest(httpRequest, ctx);
            }
        } finally {
            ReferenceCountUtil.release(msg);
        }

    }

    private void handlerTest(FullHttpRequest request, ChannelHandlerContext ctx){
        FullHttpResponse response = null;
        try{
            String value = "hello,netty";
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(value.getBytes("UTF-8")));
            response.headers().set("Content-Type", "application/json");
            response.headers().setInt("Content-Lentgth", response.content().readableBytes());
        }catch (Exception e){
            e.printStackTrace();
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NO_CONTENT);
        }finally {
            if(request != null){
                if(HttpUtil.isKeepAlive(request)){
                    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
                }else{
                    response.headers().set(HttpHeaderNames.CONNECTION, KEEP_ALIVE);
                }
            }
        }


    }


}
