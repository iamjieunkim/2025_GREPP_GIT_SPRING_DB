package io.jieun.dao.dao.datajpa;

import io.jieun.dao.global.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DataJpaOrderRepository extends JpaRepository<Orders, Long> {

    @Query("select o from Orders o where o.orderCode = :orerCode")
    Optional<Orders> findByOrderCode(String orderCode);

    void deleteByOrderCode(String orderCode);

}
