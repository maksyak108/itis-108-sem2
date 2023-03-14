package ru.kpfu.itis.dto;

import lombok.Getter;
import lombok.Setter;
import ru.kpfu.itis.model.shop.Product;

@Getter
@Setter
public class ProductDto {

    private Integer id;

    private String name;

    private Integer price;

    private Integer cartId;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
    }
}

