package io.kimmking.rpcfx.demo.provider;

import io.kimmking.rpcfx.netty.server.NettyServer;
import io.kimmking.rpcfx.netty.server.ProviderManagement;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = "io.kimmking.rpcfx")
@SpringBootApplication
public class RpcfxServerApplication implements ApplicationRunner {

	private final NettyServer nettyServer;

	public RpcfxServerApplication(NettyServer nettyServer) {
		this.nettyServer = nettyServer;
	}

	public static void main(String[] args) {
		try{
			// 注册service
			ProviderManagement.init("io.kimmking.rpcfx.demo.provider.service");
		}catch (Exception e) {
			e.printStackTrace();
		}
		SpringApplication.run(RpcfxServerApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) {
		try {
			nettyServer.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
