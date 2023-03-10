package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.model.shop.Cart;
import ru.kpfu.itis.model.shop.Product;
import ru.kpfu.itis.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/cart/{id}")
    public List<Product> getProductsByCartId(@PathVariable Integer id) {
        Cart cart = new Cart();
        cart.setId(id);
        return productService.findAllByCart(cart);
    }

    @GetMapping("/price/{price}")
    public List<Product> getProductsByPriceGreaterThanEqual(@PathVariable Integer price) {
        return productService.findAllByPriceGreaterThanEqual(price);
    }

    @GetMapping("/cart/{id}/count")
    public long countProductsByCartId(@PathVariable Integer id) {
        Cart cart = new Cart();
        cart.setId(id);
        return productService.countByCart(cart);
    }

}
