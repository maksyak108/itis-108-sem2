package ru.kpfu.itis.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.kpfu.itis.model.User;
import ru.kpfu.itis.model.shop.Client;

@Getter
@Setter
@Builder
public class ClientDto {

    private Integer id;
    private String name;

    private String email;

    public ClientDto(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public static ClientDto fromEntity(Client client) {
        return ClientDto.builder()
                .id(client.getId())
                .name(client.getName())
                .email(client.getEmail())
                .build();
    }
}
