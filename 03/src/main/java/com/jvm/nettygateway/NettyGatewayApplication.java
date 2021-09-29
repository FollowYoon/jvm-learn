package com.jvm.nettygateway;

import com.jvm.nettygateway.inbound.HttpInboundServer;

public class NettyGatewayApplication {

    public final static String GATEWAY_NAME = "NIOGateway";
    public final static String GATEWAY_VERSION = "2.0.0";

    public static void main(String[] args) {
        String proxyPort = System.getProperty("proxyPort","8888");

        String proxyServer = System.getProperty("proxyServer","http://localhost:8081");
        int port = Integer.parseInt(proxyPort);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" starting...");
        HttpInboundServer server = new HttpInboundServer(port, proxyServer);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" started at http://localhost:" + port + " for server:" + server.toString());
        try {
            server.run();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
