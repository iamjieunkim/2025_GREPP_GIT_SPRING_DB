package io.jieun.dao.dao.mybatis;

import io.jieun.dao.global.entity.Items;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest //스프링에 붙어있는거 쓰려면 이거 쓰면 됨
class ItemMapperTests {


    @Autowired ItemMapper itemMapper;

    @Test
    @DisplayName("create_test")
    void save_test() throws Exception {


        Items item = Items.builder()
                .itemCode("ITEM_45682")
                .name("ITEM_45682")
                .price(1_000)
                .build();

        //mapper를 통해서 바로 저장을 하려고 함
        itemMapper.save(item); //itemMapper를 통해 save에다가 item을 넣으면 실행이 됨
        //Invalid bound statement (not found): io.jieun.dao.dao.mybatis.ItemMapper.save이렇게 오류가 남


    }
}