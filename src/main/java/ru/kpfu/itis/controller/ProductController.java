package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.dto.ProductDto;
import ru.kpfu.itis.model.shop.Cart;
import ru.kpfu.itis.model.shop.Product;
import ru.kpfu.itis.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/cart/{id}")
    public List<ProductDto> getProductsByCartId(@PathVariable Integer id) {
        Cart cart = new Cart();
        cart.setId(id);
        List<Product> products = productService.findAllByCart(cart);
        return products.stream().map(ProductDto::new).collect(Collectors.toList());
    }

    @GetMapping("/price/{price}")
    public List<ProductDto> getProductsByPriceGreaterThanEqual(@PathVariable Integer price) {
        List<Product> products = productService.findAllByPriceGreaterThanEqual(price);
        return products.stream().map(ProductDto::new).collect(Collectors.toList());
    }

    @GetMapping("/cart/{id}/count")
    public long countProductsByCartId(@PathVariable Integer id) {
        Cart cart = new Cart();
        cart.setId(id);
        return productService.countByCart(cart);
    }

}
