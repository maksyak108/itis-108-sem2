package ru.kpfu.itis.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.model.shop.Cart;
import ru.kpfu.itis.model.shop.Product;
import ru.kpfu.itis.repository.shop.ProductRepository;
import ru.kpfu.itis.service.ProductService;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAllByCart(Cart cart) {
        return productRepository.findAllByCart(cart);
    }

    public List<Product> findAllByPriceGreaterThanEqual(Integer price) {
        return productRepository.findAllByPriceGreaterThanEqual(price);
    }

    public long countByCart(Cart cart) {
        return productRepository.countByCart(cart);
    }

}
