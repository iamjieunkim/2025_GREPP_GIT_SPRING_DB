package io.jieun.dao.dao.datajpa;


import io.jieun.dao.global.entity.OrderItems;
import io.jieun.dao.global.entity.Orders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DataJpaOrderRepositoryCombine {

    private final DataJpaOrderRepository orderRepository;
    private final DataJpaOrderItemsRepository orderItemsRepository;


    public Orders saveOrder(Orders order) {
        return orderRepository.save(order);
    }

    public List<Orders> findAllOrders() {
        return orderRepository.findAll();
    }

    public OrderItems saveOrderItems(OrderItems orderItems) {
        return orderItemsRepository.save(orderItems);
    }

}
