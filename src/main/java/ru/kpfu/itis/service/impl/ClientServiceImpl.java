package ru.kpfu.itis.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.dto.ClientDto;
import ru.kpfu.itis.dto.CreateClientRequestDto;
import ru.kpfu.itis.dto.CreateUserRequestDto;
import ru.kpfu.itis.dto.UserResponseDto;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.shop.Client;
import ru.kpfu.itis.repository.shop.ClientRepository;
import ru.kpfu.itis.service.ClientService;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService{

    @Autowired
    private ClientRepository clientRepository;

    private final BCryptPasswordEncoder encoder;

    public Client findByName(String name) {
        return clientRepository.findByName(name);
    }

    public Optional<Client> findById(Integer id) {
        return clientRepository.findById(id);
    }

    public List<Client> findByCartIsNull() {
        return clientRepository.findByCartIsNull();
    }

    @Override
    public ClientDto create(CreateClientRequestDto clientDto) {
        Client client = Client.builder()
                .name(clientDto.getName())
                .password(encoder.encode(clientDto.getPassword()))
                .build();
        return ClientDto.fromEntity(clientRepository.save(client));
    }
}
