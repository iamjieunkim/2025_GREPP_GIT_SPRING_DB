package io.jieun.dao.dao.hibernate;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.jieun.dao.global.entity.Items;
import io.jieun.dao.global.entity.OrderItems;
import io.jieun.dao.global.entity.Orders;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static io.jieun.dao.global.entity.QItems.items;

@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class HibernateOrderRepository {
    //oderitems랑 order을 이곳에서 같이 관리하도록 하겠다.
    private final EntityManager entityManager;

    public Orders saveOrder(Orders orders) {
        entityManager.persist(orders);
        return orders;
    }
    //JPQL -> order를 orderCode로 찾아 올 예정
    //orderCode 유니크
    //Optional
    public Optional<Orders> findOrderByOrderCode(String orderCode) {
        return entityManager.createQuery("select o from Orders o where o.orderCode = :orderCode", Orders.class)
                .setParameter("orderCode", orderCode)
                .getResultList()
                .stream()
                .findAny();
    }

    public Long removeOrderByOrderCode(String orderCode) {

        Optional<Orders> orderOptional = findOrderByOrderCode(orderCode);

//        if (orderOptional.isEmpty()) {
//            throw new NoSuchElementException();
//        }
//
//        Orders findOrder = orderOptional.get();

        Orders findOrder = orderOptional.orElseThrow(() -> new NoSuchElementException());
        entityManager.remove(findOrder);

        return findOrder.getId();

    }

    public OrderItems saveOrderItems(OrderItems orderItems) {
        entityManager.persist(orderItems);
        return orderItems;
    }

    public OrderItems getOrderItemsById(Long id) {
        return entityManager.find(OrderItems.class, id);
    }

    public List<OrderItems> saveAllOrderItems(List<OrderItems> orderItems) {

        int batchSize = 50;

        for ( int i = 0; i < orderItems.size(); i++ ) {

            entityManager.persist(orderItems.get(i));

            if ( i % batchSize == 0 && i > 0 ) {
                entityManager.flush();
                entityManager.clear();
            }

        }

        entityManager.flush();
        entityManager.clear();

        return orderItems;

    }


}
