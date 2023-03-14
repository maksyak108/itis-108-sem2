package ru.kpfu.itis.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kpfu.itis.model.User;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> getUserByEmail(String email);

    List<User> findAllByIdInAAndEmailNotN(List<Integer> ids);

    Page<User> findAll(Pageable pageable);

    @Query(value = "select * from user u where u.name like ?1",nativeQuery = true)
    List<User> findAllByName(String name);

    @Query("select u from User u where u.email = :email")
    List<User> fingAllByEmail(String email);

    @Query(value = "select u from User u where u.name = :name and u.id = :id")
    User findUserByNameAndId(@Param("name") String name, @Param("id") Integer id);

}
