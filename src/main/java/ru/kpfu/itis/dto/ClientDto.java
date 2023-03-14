package ru.kpfu.itis.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDto {

    private Integer id;
    private String name;

    public ClientDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
