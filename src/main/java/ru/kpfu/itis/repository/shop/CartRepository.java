package ru.kpfu.itis.repository.shop;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.model.shop.Cart;
import ru.kpfu.itis.model.shop.Client;
import ru.kpfu.itis.model.shop.Shop;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {

    List<Cart> findAllByClient(Client client);

    @EntityGraph(attributePaths = "products")
    Optional<Cart> findById(Integer id);

    List<Cart> findAllByShopsContaining(Shop shop);
}
