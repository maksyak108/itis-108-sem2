package ru.kpfu.itis.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShopDto {

    private Integer id;
    private String name;

    public ShopDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
