package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.TypedQuery;
import java.util.List;

public interface UserService {

    User findByUsername(String username);

    List<User> showUsers();

    User findUserById(Long id);

    void saveUser(User user);

    void updateUserById(Long id, User user);

    void deleteById(Long id);

    List<Role> findRoles();

}
