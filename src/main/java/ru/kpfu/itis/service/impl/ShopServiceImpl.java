package ru.kpfu.itis.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.shop.Cart;
import ru.kpfu.itis.model.shop.Shop;
import ru.kpfu.itis.repository.shop.ShopRepository;
import ru.kpfu.itis.service.ShopService;

import java.util.List;

@Service
@AllArgsConstructor
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopRepository shopRepository;

    @Override
    public Shop findByName(String name) {
        return shopRepository.findByName(name);
    }

    @Override
    public List<Cart> findAllCartsByShopId(Integer id) {
        return shopRepository.findAllCartsByShopId(id);
    }

    @Override
    public List<Shop> findAllWithoutCarts() {
        return shopRepository.findAllWithoutCarts();
    }
}
