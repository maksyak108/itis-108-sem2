package ru.kpfu.itis.model.shop;

import lombok.*;
import ru.kpfu.itis.model.shop.Cart;

import javax.persistence.*;

@Entity
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Client {

    private String name;
    @OneToOne(mappedBy = "client")
    private Cart cart;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
