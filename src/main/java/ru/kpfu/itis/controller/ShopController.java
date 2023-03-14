package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.dto.CartDto;
import ru.kpfu.itis.dto.ShopDto;
import ru.kpfu.itis.model.shop.Shop;
import ru.kpfu.itis.model.shop.Cart;
import ru.kpfu.itis.service.ShopService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shops")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/{name}")
    public ResponseEntity<ShopDto> getShopByName(@PathVariable String name) {
        Shop shop = shopService.findByName(name);
        if (shop == null) {
            return ResponseEntity.notFound().build();
        }
        ShopDto shopDto = new ShopDto(shop.getId(), shop.getName());
        return ResponseEntity.ok(shopDto);
    }

    @GetMapping("/{id}/carts")
    public ResponseEntity<List<CartDto>> getAllCartsByShopId(@PathVariable Integer id) {
        List<Cart> carts = shopService.findAllCartsByShopId(id);
        if (carts == null || carts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<CartDto> cartDto = carts.stream()
                .map(CartDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(cartDto);
    }

    @GetMapping("/without-carts")
    public ResponseEntity<List<ShopDto>> getAllShopsWithoutCarts() {
        List<Shop> shops = shopService.findAllWithoutCarts();
        if (shops == null || shops.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<ShopDto> shopDto = shops.stream()
                .map(shop -> new ShopDto(shop.getId(), shop.getName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(shopDto);
    }
}
