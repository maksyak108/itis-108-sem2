package ru.kpfu.itis.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.shop.Cart;
import ru.kpfu.itis.model.shop.Client;
import ru.kpfu.itis.model.shop.Shop;
import ru.kpfu.itis.repository.shop.CartRepository;
import ru.kpfu.itis.service.CartService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService{

    private CartRepository cartRepository;

    public List<Cart> findAllByClient(Client client) {
        return cartRepository.findAllByClient(client);
    }

    public Optional<Cart> findById(Integer id) {
        return cartRepository.findById(id);
    }

    public List<Cart> findAllByShopsContaining(Shop shop) {
        return cartRepository.findAllByShopsContaining(shop);
    }

}
