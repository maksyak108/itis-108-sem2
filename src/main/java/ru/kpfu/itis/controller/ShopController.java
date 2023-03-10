package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.model.shop.Shop;
import ru.kpfu.itis.model.shop.Cart;
import ru.kpfu.itis.service.ShopService;

import java.util.List;

@RestController
@RequestMapping("/shops")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping("/{name}")
    public ResponseEntity<Shop> getShopByName(@PathVariable String name) {
        Shop shop = shopService.findByName(name);
        if (shop == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(shop);
    }

    @GetMapping("/{id}/carts")
    public ResponseEntity<List<Cart>> getAllCartsByShopId(@PathVariable Integer id) {
        List<Cart> carts = shopService.findAllCartsByShopId(id);
        if (carts == null || carts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carts);
    }

    @GetMapping("/without-carts")
    public ResponseEntity<List<Shop>> getAllShopsWithoutCarts() {
        List<Shop> shops = shopService.findAllWithoutCarts();
        if (shops == null || shops.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(shops);
    }
}
