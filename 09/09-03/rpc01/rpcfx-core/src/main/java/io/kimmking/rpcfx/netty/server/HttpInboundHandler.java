package io.kimmking.rpcfx.netty.server;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import io.kimmking.rpcfx.api.RpcfxNettyDTO;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.kimmking.rpcfx.exception.RpcfxException;
import io.kimmking.rpcfx.server.ApplicationContextUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author qindong
 * @date 2021/9/28 15:06
 */
@Slf4j
public class HttpInboundHandler extends SimpleChannelInboundHandler<RpcfxNettyDTO> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcfxNettyDTO rpcfxNettyDTO) throws Exception {
        RpcfxRequest rpcRequest = JSON.parseObject(new String(rpcfxNettyDTO.getContent(), CharsetUtil.UTF_8),
                RpcfxRequest.class);
        log.info("Netty server rpcRequest : " + rpcRequest.toString());

        // 反射调用方法，获取结果
        RpcfxResponse response = invoke(rpcRequest);

        // 返回结果给netty 客户端
        RpcfxNettyDTO message = new RpcfxNettyDTO();
        String requestJson = JSON.toJSONString(response);
        message.setLength(requestJson.getBytes(CharsetUtil.UTF_8).length);
        message.setContent(requestJson.getBytes(CharsetUtil.UTF_8));

        channelHandlerContext.writeAndFlush(message).sync();
        log.info("Netty server return response to client end");
    }

    public RpcfxResponse invoke(RpcfxRequest request) {
        RpcfxResponse response = new RpcfxResponse();
        String serviceClass = request.getServiceClass();
        // 作业1：改成泛型和反射
        Class<?> service;
        try{
            service = Class.forName(serviceClass);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            response.setException(new RpcfxException(e.getMessage()));
            response.setStatus(false);
            return response;
        }
        try {
            Method method = resolveMethodFromClass(service, request.getMethod());
            Object result = method.invoke(ApplicationContextUtil.getApplicationContext().getBean(service), request.getParams());
            // 两次json序列化能否合并成一个
            response.setResult(JSON.toJSONString(result, SerializerFeature.WriteClassName));
            response.setStatus(true);
            return response;
        } catch (IllegalAccessException | InvocationTargetException e) {

            // 3.Xstream

            // 2.封装一个统一的RpcfxException
            // 客户端也需要判断异常
            e.printStackTrace();
            response.setException(new RpcfxException(e.getMessage()));
            response.setStatus(false);
            return response;
        }
    }

    private Method resolveMethodFromClass(Class<?> klass, String methodName) {
        return Arrays.stream(klass.getMethods()).filter(m -> methodName.equals(m.getName())).findFirst().get();
    }

}
