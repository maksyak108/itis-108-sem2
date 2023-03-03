package ru.kpfu.itis.service;

import ru.kpfu.itis.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> findAll();
}
