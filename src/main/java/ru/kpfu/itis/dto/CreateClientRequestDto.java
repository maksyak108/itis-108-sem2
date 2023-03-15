package ru.kpfu.itis.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CreateClientRequestDto {
    @NotBlank(message = "Name shouldn't be blank")
    private String name;

    @Size(min = 8, max = 63, message = "Password should contains from 8 to 63 symbols")
    private String password;
}
