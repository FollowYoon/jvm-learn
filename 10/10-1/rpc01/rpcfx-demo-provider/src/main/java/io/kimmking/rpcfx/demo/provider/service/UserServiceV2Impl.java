package io.kimmking.rpcfx.demo.provider.service;

import io.kimmking.rpcfx.annotation.Provider;
import io.kimmking.rpcfx.api.User;
import io.kimmking.rpcfx.api.UserService;

@Provider(serviceName = "io.kimmking.rpcfx.api.UserService", group = "group1", version = "v2")
public class UserServiceV2Impl implements UserService {

    @Override
    public User findById(int id) {
        return new User(id, "KK" + System.currentTimeMillis()+" V2");
    }
}
