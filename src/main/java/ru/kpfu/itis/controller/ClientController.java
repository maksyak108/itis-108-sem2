package ru.kpfu.itis.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.dto.ClientDto;
import ru.kpfu.itis.dto.CreateClientRequestDto;
import ru.kpfu.itis.model.shop.Client;
import ru.kpfu.itis.service.ClientService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/clientID")
    public ResponseEntity<?> searchClientById(@RequestParam("id") String id) {
        Optional<Client> client = clientService.findById(Integer.valueOf(id));
        if (client.isPresent()) {
            ClientDto clientDto = new ClientDto(client.get().getId(), client.get().getName(), client.get().getEmail());
            return ResponseEntity.ok(clientDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/name")
    public ResponseEntity<?> getClientByName(@RequestParam("name") String name) {
        Client client = clientService.findByName(name);
        if (client != null) {
            ClientDto clientDto = new ClientDto(client.getId(), client.getName(), client.getEmail());
            return ResponseEntity.ok(clientDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @GetMapping("/no-cart")
    public List<ClientDto> getClientsWithNoCart() {
        List<Client> clients = clientService.findByCartIsNull();
        return clients.stream().map(c -> new ClientDto(c.getId(), c.getName(), c.getEmail())).collect(Collectors.toList());
    }

    @PostMapping("/registerClient")
    public String createClient(@ModelAttribute CreateClientRequestDto client, HttpServletRequest request) {
        String url = request.getRequestURL().toString().replace(request.getServletPath(), "");
        clientService.create(client, url);
        return "sign_up_success";
    }
    @GetMapping("/verification")
    public String verify(@Param("code") String code) {
        if (clientService.verify(code)) {
            return "verification_success";
        } else {
            return "verification_failed";
        }
    }
}
