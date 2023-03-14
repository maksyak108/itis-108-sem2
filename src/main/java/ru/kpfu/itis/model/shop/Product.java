package ru.kpfu.itis.model.shop;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import ru.kpfu.itis.model.shop.Cart;

import javax.persistence.*;

@Entity(name = "products")
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 16)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinColumn(name = "cart_id")
    private Cart cart;
}
