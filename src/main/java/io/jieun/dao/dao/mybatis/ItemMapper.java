package io.jieun.dao.dao.mybatis;

import io.jieun.dao.global.entity.Items;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface ItemMapper {

    //void save(@Param("name") String name, @Param("itemCode") String itemCode, @Param("price") Integer price);
    void save(Items items);
    int update(@Param("itemCode")String itemCode, @Param("price") Integer price);
    Optional<Items> findByItemCode(@Param("itemCode")String itemCode);
    void remove(Items items);

}
