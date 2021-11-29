package io.kimmking.rpcfx.netty.client;

import com.alibaba.fastjson.JSON;
import io.kimmking.rpcfx.api.RpcfxNettyDTO;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * 这里使用并发的等待-通知机制来拿到结果
 * @author lw
 */
@Slf4j
public class RpcClientSyncHandler extends SimpleChannelInboundHandler<RpcfxNettyDTO> {

    private CountDownLatch latch;
    private RpcfxResponse response;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcfxNettyDTO msg) {
        log.info("Netty client receive message:");
        log.info(msg.toString());
        // 将 RpcResponse字符串 反序列化成 RpcResponse对象
        RpcfxResponse rpcfxResponse = JSON.parseObject(new String(msg.getContent(), CharsetUtil.UTF_8), RpcfxResponse.class);

        response = rpcfxResponse;
        latch.countDown();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * 锁的初始化
     * @param latch CountDownLatch
     */
    void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    /**
     * 阻塞等待结果后返回
     * @return 后台服务器响应
     * @throws InterruptedException exception
     */
    RpcfxResponse getResponse() throws InterruptedException {
        latch.await();
        return response;
    }
}
