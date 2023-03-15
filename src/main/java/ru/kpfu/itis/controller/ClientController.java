package ru.kpfu.itis.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.dto.ClientDto;
import ru.kpfu.itis.dto.CreateClientRequestDto;
import ru.kpfu.itis.model.shop.Client;
import ru.kpfu.itis.service.ClientService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @ResponseBody
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

    @ResponseBody
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

    @ResponseBody
    @GetMapping("/no-cart")
    public List<ClientDto> getClientsWithNoCart() {
        List<Client> clients = clientService.findByCartIsNull();
        return clients.stream().map(c -> new ClientDto(c.getId(), c.getName())).collect(Collectors.toList());
    }

    @PostMapping("/registerClient")
    public String createClient(@ModelAttribute CreateClientRequestDto client) {
        clientService.create(client);
        return "sign_up_success";
    }
}
