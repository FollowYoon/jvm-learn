package io.kimmking.rpcfx.demo.provider.service;

import io.kimmking.rpcfx.annotation.Provider;
import io.kimmking.rpcfx.api.User;
import io.kimmking.rpcfx.api.UserService;
import org.springframework.stereotype.Service;

@Provider(serviceName = "io.kimmking.rpcfx.api.UserService")
public class UserServiceImpl implements UserService {

    @Override
    public User findById(int id) {
        return new User(id, "KK" + System.currentTimeMillis());
    }
}
