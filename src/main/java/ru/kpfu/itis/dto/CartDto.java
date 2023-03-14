package ru.kpfu.itis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import ru.kpfu.itis.model.shop.Cart;
import ru.kpfu.itis.model.shop.Client;
import ru.kpfu.itis.model.shop.Product;
import ru.kpfu.itis.model.shop.Shop;

import java.util.List;

@Getter
@Setter
public class CartDto {

    private Integer id;

    private List<Product> products;

    private Client client;

    private List<Shop> shops;

    public CartDto(Cart cart) {
        this.id = cart.getId();
        this.products = cart.getProducts();
        this.client = cart.getClient();
        this.shops = cart.getShops();
    }
}
