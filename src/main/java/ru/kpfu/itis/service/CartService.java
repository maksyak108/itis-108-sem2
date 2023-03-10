package ru.kpfu.itis.service;

import ru.kpfu.itis.model.shop.Cart;
import ru.kpfu.itis.model.shop.Client;
import ru.kpfu.itis.model.shop.Shop;

import java.util.List;
import java.util.Optional;

public interface CartService {

    List<Cart> findAllByClient(Client client);

    Optional<Cart> findById(Integer id);

    List<Cart> findAllByShopsContaining(Shop shop);
}
