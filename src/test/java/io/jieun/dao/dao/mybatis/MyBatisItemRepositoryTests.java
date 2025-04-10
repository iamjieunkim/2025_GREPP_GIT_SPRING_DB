package io.jieun.dao.dao.mybatis;

import io.jieun.dao.global.entity.Items;
import io.jieun.dao.util.TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

        Items item = Items.builder()
                .name(TestUtils.genRandomItemCode())
                .itemCode(TestUtils.genRandomItemCode())
                .price(TestUtils.genRandomPrice())
                .build();

        //가정을 해야함 리포지터리에서 save라는 메소드를 호출했을때, 어떻게 반응할껀지?
        doNothing().when(mapper).save(item);

        Items saved = repository.save(item);

        assertThat(saved.getItemCode()).isEqualTo(item.getItemCode());
        verify(mapper, times(1)).save(item);

    }

    @Test
    @DisplayName("itemcode가 없는 item을 save하면 오류가 발생할 것이다.")
    void raise_exception_test_1() throws Exception {

        Items item = Items.builder()
                .name(TestUtils.genRandomItemCode())
                .itemCode(TestUtils.genRandomItemCode())
                .price(TestUtils.genRandomPrice())
                .build();

        //코드가 안들어 있는 코드가 들어오면 예외를 터지게 해서 알려줘
        doThrow(RuntimeException.class).when(mapper).save(item);

        assertThatThrownBy(
                () -> {
                    repository.save(item);
                }
        ).isInstanceOf(RuntimeException.class);

        verify(mapper, times(1)).save(item);

    }



}