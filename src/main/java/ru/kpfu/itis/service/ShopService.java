package ru.kpfu.itis.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kpfu.itis.model.shop.Cart;
import ru.kpfu.itis.model.shop.Shop;

import java.util.List;

public interface ShopService {

    Shop findByName(String name);

    @Query(value = "SELECT s.carts FROM shops s WHERE s.id = :id")
    List<Cart> findAllCartsByShopId(@Param("id") Integer id);

    @Query(value = "SELECT s FROM shops s WHERE s.carts IS EMPTY")
    List<Shop> findAllWithoutCarts();
}
