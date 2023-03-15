package ru.kpfu.itis.model.shop;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import ru.kpfu.itis.model.Role;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Client {

    private String name;
    @JsonBackReference
    @OneToOne(mappedBy = "client")
    private Cart cart;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 63)
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "client_role",
            joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;
}
