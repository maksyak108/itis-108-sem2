package ru.kpfu.itis.model.shop;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonBackReference
    private List<Product> products;

    @OneToOne
    @JsonManagedReference
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToMany(mappedBy = "carts")
    @JsonBackReference
    private List<Shop> shops;
}
