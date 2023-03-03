package ru.kpfu.itis.repository.shop;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.model.shop.Cart;
import ru.kpfu.itis.model.shop.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findAllByCart(Cart cart);

    List<Product> findAllByPriceGreaterThanEqual(Integer price);

    long countByCart(Cart cart);
}
