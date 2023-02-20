package ru.kpfu.itis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {

}
