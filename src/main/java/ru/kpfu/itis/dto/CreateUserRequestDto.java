package ru.kpfu.itis.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@Builder
public class CreateUserRequestDto {

    @NotBlank(message = "Name shouldn't be blank")
    @Pattern(regexp = "[a-zA-Z]{1,15}")
    private String name;
    @NotBlank(message = "Second name shouldn't be blank")
    @Pattern(regexp = "[a-zA-Z]{1,18}")
    private String secondname;

   @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")
    private String email;

    @Past
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;
}
