package io.jieun.dao.dao.mybatis;

import io.jieun.dao.global.entity.Items;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class MyBatisItemRepositoryTests {
    
    @Test
    @DisplayName("상품등록서비스")
    void item_save_test() throws Exception {
    
        new Items("CODE", "NAME", 1000);
    
    }

}