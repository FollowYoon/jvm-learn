package io.kimmking.rpcfx.demo.consumer;

import io.kimmking.rpcfx.client.Rpcfx;
import io.kimmking.rpcfx.api.User;
import io.kimmking.rpcfx.api.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class RpcfxClientApplication {

	public static void main(String[] args) {
		//SpringApplication.run(RpcfxClientApplication.class, args);

		UserService userService = Rpcfx.create(UserService.class, "http://localhost:8080/");
		User user = userService.findById(1);
		System.out.println("find user id=1 from server: " + user.getName());

	}

}



