package io.jieun.dao.dao.datajpa;

import io.jieun.dao.global.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DataJpaOrderItemsRepository extends JpaRepository<OrderItems, Long> {

    @Query("select oi from OrderItems oi where oi.orders.orderCode = :orderCode")
    List<OrderItems> findAllByOrderCode(String orderCode);

}
