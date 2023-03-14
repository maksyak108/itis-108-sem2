package ru.kpfu.itis.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

//@Data
//@Builder
//public class CreateUserRequestDto {
//
//    @NotBlank(message = "Name shouldn't be blank")
//    @Pattern(regexp = "[a-zA-Z]{1,15}")
//    private String name;
//    @NotBlank(message = "Second name shouldn't be blank")
//    @Pattern(regexp = "[a-zA-Z]{1,18}")
//    private String secondname;
//
//   @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")
//    private String email;
//
//    @Past
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private LocalDate birth;
//
//    @Size(min = 8, max = 63, message = "Password should contains from 8 to 63 symbols")
//    private String password;
//}
@Data
public class CreateUserRequestDto {

    @NotBlank(message = "Name shouldn't be blank")
    private String name;

    @NotBlank(message = "Email shouldn't be blank")
    private String email;

    @Size(min = 8, max = 63, message = "Password should contains from 8 to 63 symbols")
    private String password;
}