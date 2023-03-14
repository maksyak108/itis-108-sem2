package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.dto.CartDto;
import ru.kpfu.itis.model.shop.Cart;
import ru.kpfu.itis.model.shop.Client;
import ru.kpfu.itis.model.shop.Shop;
import ru.kpfu.itis.service.CartService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/client/{clientId}")
    public List<CartDto> findAllByClient(@PathVariable Integer clientId) {
        Client client = new Client();
        client.setId(clientId);
        List<Cart> carts = cartService.findAllByClient(client);
        return carts.stream().map(CartDto::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Optional<CartDto> findById(@PathVariable Integer id) {
        Optional<Cart> cart = cartService.findById(id);
        return cart.map(CartDto::new);
    }

    @GetMapping("/shop/{shopId}")
    public List<CartDto> findAllByShopsContaining(@PathVariable Integer shopId) {
        Shop shop = new Shop();
        shop.setId(shopId);
        List<Cart> carts = cartService.findAllByShopsContaining(shop);
        return carts.stream().map(CartDto::new).collect(Collectors.toList());
    }
}
