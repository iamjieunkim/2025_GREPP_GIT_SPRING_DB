package io.jieun.dao.dao.mybatis;

import io.jieun.dao.global.entity.Items;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
class MyBatisItemRepositoryTests {

    @Mock
    ItemMapper mapper;
    MyBatisItemRepository repository;

    @BeforeEach
    void init(){
        repository = new MyBatisItemRepository(mapper);
    }

    @Test
    @DisplayName("상품등록서비스")
    void item_save_test() throws Exception {

        repository.save(null);

    }

}