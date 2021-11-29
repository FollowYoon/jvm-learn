package io.kimmking.rpcfx.client;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import io.kimmking.rpcfx.api.*;
import io.kimmking.rpcfx.exception.RpcfxException;
import io.kimmking.rpcfx.netty.client.NettyClient;
import okhttp3.MediaType;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.io.IOException;
import java.lang.reflect.Method;

public final class Rpcfx {

    public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");

    static {
        ParserConfig.getGlobalInstance().addAccept("io.kimmking");
    }

    public static <T> T create(final Class<T> serviceClass, final String url, Filter... filters) {
        // 0. 替换动态代理 -> 字节码生成
        //return (T) Proxy.newProxyInstance(Rpcfx.class.getClassLoader(), new Class[]{serviceClass}, new RpcfxInvocationHandler(serviceClass, url, filters));

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(serviceClass);
        enhancer.setCallback(new RpcIntercept(serviceClass.getName(), url, null, null));
        return (T) enhancer.create();
    }

    public static <T> T create(final Class<T> serviceClass, final String url, final String group, final String version) {
        StringBuilder providerName = new StringBuilder();
        providerName.append(serviceClass.getName()).append(":").append(group).append(":").append(version);
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(serviceClass);
        enhancer.setCallback(new RpcIntercept(serviceClass.getName(), url, group, version));
        return (T) enhancer.create();
    }



    public static class RpcIntercept implements MethodInterceptor {

        private final String className;
        private final String url;
        private final String group;
        private final String version;

        public RpcIntercept(String serviceClassName, String rpcUrl, String group, String version) {
            this.className = serviceClassName;
            this.url = rpcUrl;
            this.group = group;
            this.version = version;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            RpcfxRequest request = new RpcfxRequest();
            request.setServiceClass(className);
            request.setMethod(method.getName());
            request.setParams(objects);
            request.setGroup(group);
            request.setVersion(version);
            RpcfxResponse response = post(request, url);
            if(!response.isStatus()){
                throw new RpcfxException("请求异常!");
            }
            return JSON.parse(response.getResult().toString());
        }
    }

    public static RpcfxResponse post(RpcfxRequest req, String url) throws IOException {
        String reqJson = JSON.toJSONString(req);
        System.out.println("req json: "+reqJson);

        // 1.可以复用client
        // 2.尝试使用httpclient或者netty client
        /*OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(JSONTYPE, reqJson))
                .build();
        String respJson = client.newCall(request).execute().body().string();
        System.out.println("resp json: "+respJson);
        return JSON.parseObject(respJson, RpcfxResponse.class);*/

        NettyClient nettyClient = NettyClient.getInstance();
        RpcfxResponse response;
        try {
            response = nettyClient.getResponse(req, url);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        System.out.println("resp json: " + response.toString());
        return response;

    }
}
