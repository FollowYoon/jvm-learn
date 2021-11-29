package io.kimmking.rpcfx.netty;

import io.kimmking.rpcfx.api.RpcfxNettyDTO;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author qindong
 * @date 2021/11/17 14:28
 */
public class RpcEncoder extends MessageToByteEncoder<RpcfxNettyDTO> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, RpcfxNettyDTO rpcfxNettyDTO, ByteBuf byteBuf) throws Exception {
        byteBuf.writeInt(rpcfxNettyDTO.getLength());
        byteBuf.writeBytes(rpcfxNettyDTO.getContent());
    }

}
