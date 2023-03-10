package ru.kpfu.itis.model.shop;

import lombok.Getter;
import lombok.Setter;
import ru.kpfu.itis.model.shop.Cart;

import javax.persistence.*;
import java.util.List;

@Entity(name = "shops")
@Getter
@Setter
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 16)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "shop_carts",
            joinColumns = @JoinColumn(name = "shop_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "cart_id", referencedColumnName = "id")
    )
    private List<Cart> carts;
}
