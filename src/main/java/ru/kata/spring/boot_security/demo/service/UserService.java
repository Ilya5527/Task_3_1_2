package ru.kata.spring.boot_security.demo.service;



import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.List;

public interface UserService extends UserDetailsService {


    void addUser(User user);

    void deleteUser(long id);

    List<User> getAllUsers();

    User getUserById(long id);

    void updateUser(User changedUser);
}
