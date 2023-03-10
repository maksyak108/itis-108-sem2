package ru.kpfu.itis.service;

import ru.kpfu.itis.model.shop.Client;
import ru.kpfu.itis.repository.shop.ClientRepository;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    Client findByName(String name);
    Optional<Client> findById(Integer id);

    List<Client> findByCartIsNull();
}
