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

import java.util.NoSuchElementException;
import java.util.Optional;

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

    @Test
    @DisplayName("유효한 itemcode는 item을 조회할 수 있고 그렇지 않으면 조회가 불가능하다.")
    void find_by_item_code_test() throws Exception {

        String INVALID_ITEM_CODE = "INVALID_ITEM_CODE";
        String VALID_ITEM_CODE = TestUtils.genRandomItemCode(); // 예: "ITEM_24833"

        Items validItem = Items.builder()
                .name(TestUtils.genRandomItemCode())
                .itemCode(VALID_ITEM_CODE) // 요기!
                .price(TestUtils.genRandomPrice())
                .build();



        when(mapper.findByItemCode(VALID_ITEM_CODE)).thenReturn(Optional.of(validItem));
        when(mapper.findByItemCode(INVALID_ITEM_CODE)).thenReturn(Optional.empty());

        Optional<Items> validItemOptional = repository.findByItemCode(VALID_ITEM_CODE);
        assertThat(validItemOptional.isPresent()).isTrue();
        assertThat(validItemOptional.get()).isEqualTo(validItem);
        assertThat(validItemOptional.get().getItemCode()).isEqualTo(VALID_ITEM_CODE);

        Optional<Items> invalidItemOptional = repository.findByItemCode(INVALID_ITEM_CODE);
        assertThat(invalidItemOptional.isPresent()).isFalse();

        assertThatThrownBy(
                () -> {
                    invalidItemOptional.get();
                }
        ).isInstanceOf(NoSuchElementException.class );

        verify(mapper, times(1)).findByItemCode(VALID_ITEM_CODE);


    }

    @Test
    @DisplayName("item code와 변경하고자 하는 가격이 주어지면 변경된다.")
    void it_will_change() throws Exception {

        final String VALID_ITEM_CODE = TestUtils.genRandomItemCode();
        final String INVALID_ITEM_CODE = "INVALID_ITEM_CODE";

        final int TARGET_PRICE = 10_000;

        Items updated = Items.builder()
                .name(VALID_ITEM_CODE)
                .itemCode(VALID_ITEM_CODE) // 요기!
                .price(TARGET_PRICE)
                .build();

        when(mapper.update(any(String.class), any(Integer.class))).thenReturn(1);
        when(mapper.findByItemCode(VALID_ITEM_CODE)).thenReturn(Optional.of(updated));

        repository.updatePrice(VALID_ITEM_CODE, TARGET_PRICE);

        Optional<Items> itemOptional = repository.findByItemCode(VALID_ITEM_CODE);

        assertThat(itemOptional.isPresent()).isTrue();
        assertThat(itemOptional.get()).isEqualTo(updated);
        assertThat(itemOptional.get().getPrice()).isEqualTo(TARGET_PRICE);

        when(mapper.findByItemCode(INVALID_ITEM_CODE)).thenReturn(Optional.empty());
        Optional<Items> invaildItemOptional = repository.findByItemCode(INVALID_ITEM_CODE);
        assertThat(invaildItemOptional.isPresent()).isFalse();
        assertThatThrownBy(
                () -> {
                    invaildItemOptional.get();
                }
        ).isInstanceOf(NoSuchElementException.class);



    }


}