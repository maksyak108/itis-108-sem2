package ru.kpfu.itis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.dto.ClientDto;
import ru.kpfu.itis.model.shop.Client;
import ru.kpfu.itis.service.ClientService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;


    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable Integer id) {
        Optional<Client> client = clientService.findById(id);
        if (client.isPresent()) {
            ClientDto clientDto = new ClientDto(client.get().getId(), client.get().getName());
            return ResponseEntity.ok(clientDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ClientDto> getClientByName(@PathVariable String name) {
        Client client = clientService.findByName(name);
        if (client != null) {
            ClientDto clientDto = new ClientDto(client.getId(), client.getName());
            return ResponseEntity.ok(clientDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/no-cart")
    public List<ClientDto> getClientsWithNoCart() {
        List<Client> clients = clientService.findByCartIsNull();
        return clients.stream().map(c -> new ClientDto(c.getId(), c.getName())).collect(Collectors.toList());
    }

}
