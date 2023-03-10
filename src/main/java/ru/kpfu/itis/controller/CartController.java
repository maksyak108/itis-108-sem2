package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.model.shop.Cart;
import ru.kpfu.itis.model.shop.Client;
import ru.kpfu.itis.model.shop.Shop;
import ru.kpfu.itis.service.CartService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/client/{clientId}")
    public List<Cart> findAllByClient(@PathVariable Integer clientId) {
        Client client = new Client();
        client.setId(clientId);
        return cartService.findAllByClient(client);
    }

    @GetMapping("/{id}")
    public Optional<Cart> findById(@PathVariable Integer id) {
        return cartService.findById(id);
    }

    @GetMapping("/shop/{shopId}")
    public List<Cart> findAllByShopsContaining(@PathVariable Integer shopId) {
        Shop shop = new Shop();
        shop.setId(shopId);
        return cartService.findAllByShopsContaining(shop);
    }
}
