package ru.kpfu.itis.repository.shop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.shop.Cart;
import ru.kpfu.itis.model.shop.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findAllByCart(Cart cart);

    List<Product> findAllByPriceGreaterThanEqual(Integer price);

    long countByCart(Cart cart);
}
