package io.kimmking.rpcfx.demo.provider.service;

import io.kimmking.rpcfx.api.Order;
import io.kimmking.rpcfx.api.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public Order findOrderById(int id) {
        return new Order(id, "Cuijing" + System.currentTimeMillis(), 9.9f);
    }
}
