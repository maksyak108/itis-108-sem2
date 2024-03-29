package ru.kpfu.itis.service;

import ru.kpfu.itis.dto.ClientDto;
import ru.kpfu.itis.dto.CreateClientRequestDto;
import ru.kpfu.itis.dto.CreateUserRequestDto;
import ru.kpfu.itis.dto.UserResponseDto;
import ru.kpfu.itis.model.shop.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    Client findByName(String name);
    Optional<Client> findById(Integer id);

    List<Client> findByCartIsNull();
    ClientDto create(CreateClientRequestDto clientDto, String url);

    boolean verify(String verificationCode);

    void sendVerificationMail(String mail, String name, String code, String url);
}
