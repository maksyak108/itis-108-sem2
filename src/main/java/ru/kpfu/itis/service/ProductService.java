package ru.kpfu.itis.service;

import ru.kpfu.itis.model.shop.Cart;
import ru.kpfu.itis.model.shop.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAllByCart(Cart cart);

    List<Product> findAllByPriceGreaterThanEqual(Integer price);

    long countByCart(Cart cart);

}
