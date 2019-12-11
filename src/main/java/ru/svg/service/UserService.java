package ru.svg.service;

import ru.svg.entities.User;

import java.util.List;

public interface UserService {

    User register(User user);

    List<User> getAll();

    User findByLogin(String login);
}
