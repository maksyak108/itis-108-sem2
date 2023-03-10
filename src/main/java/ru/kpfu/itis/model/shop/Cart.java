package ru.kpfu.itis.model.shop;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "carts")
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER)
    private List<Product> products;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToMany(mappedBy = "carts")
    private List<Shop> shops;
}
