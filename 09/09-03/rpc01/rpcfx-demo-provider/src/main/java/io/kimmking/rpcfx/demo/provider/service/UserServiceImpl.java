package io.kimmking.rpcfx.demo.provider.service;

import io.kimmking.rpcfx.api.User;
import io.kimmking.rpcfx.api.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User findById(int id) {
        return new User(id, "KK" + System.currentTimeMillis());
    }
}
