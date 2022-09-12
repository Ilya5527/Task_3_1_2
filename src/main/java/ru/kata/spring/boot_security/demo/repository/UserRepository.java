package ru.kata.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    void saveUser (User user);

    void deleteById (long id);

    User findByUsername(String username);

    void updateUser (User user);

    List<User> findAll ();

    User findById (long id);
}
