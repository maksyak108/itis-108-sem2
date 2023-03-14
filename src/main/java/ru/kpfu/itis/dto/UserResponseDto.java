package ru.kpfu.itis.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import ru.kpfu.itis.model.User;

import java.time.LocalDate;

@Data
@Getter
@Builder
public class UserResponseDto {

    private Integer id;

    private String name;

    private String email;

    public static UserResponseDto fromEntity(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
//public class UserResponseDto {
//    private Integer id;
//
//    private String name;
//    private String secondname;
//
//    private String email;
//
//    private LocalDate birth;
//
//    public static UserResponseDto fromEntity(User user) {
//        return UserResponseDto.builder()
//                .id(user.getId())
//                .name(user.getName())
//                .email(user.getEmail())
//                .birth(user.getBirth())
//                .build();
//    }
//}
