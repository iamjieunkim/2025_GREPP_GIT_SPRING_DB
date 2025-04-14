package io.jieun.dao.dao.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.jieun.dao.global.entity.Items;
import io.jieun.dao.global.entity.Orders;
import io.jieun.dao.global.entity.QItems;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static io.jieun.dao.global.entity.QItems.items;

@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class QueryDslItemRepository {

    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;

    public Optional<Items> findByItemCode(String itemCode) {

//        String s = "select i from Items i where i.itemCode = :itemCode";

        Items findItem = queryFactory.selectFrom(QItems.items).where(items.itemCode.eq(itemCode)).fetchFirst();


        return Optional.ofNullable(findItem);
    }

}
