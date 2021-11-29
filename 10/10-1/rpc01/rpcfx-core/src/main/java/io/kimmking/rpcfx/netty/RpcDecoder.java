package io.kimmking.rpcfx.netty;

import io.kimmking.rpcfx.api.RpcfxNettyDTO;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author qindong
 * @date 2021/11/17 14:29
 */
public class RpcDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int length = byteBuf.readInt();
        byte[] content = new byte[length];
        if (byteBuf.readableBytes() >= length) {
            byteBuf.readBytes(content);
            RpcfxNettyDTO rpcfxNettyDTO = new RpcfxNettyDTO();
            rpcfxNettyDTO.setLength(length);
            rpcfxNettyDTO.setContent(content);
            list.add(rpcfxNettyDTO);
        }
    }
}
