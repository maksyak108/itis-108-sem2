package ru.kpfu.itis.repository.shop;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.model.shop.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client,Integer> {

    Client findByName(String name);

    Optional<Client> findById(Integer id);

    List<Client> findByCartIsNull();
}
