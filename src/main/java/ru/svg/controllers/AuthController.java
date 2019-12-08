package ru.svg.controllers;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.svg.entities.User;
import ru.svg.responces.AuthResponse;
import ru.svg.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @RequestMapping(value = "/login")
    public AuthResponse login(String login,
                              String password) {

        AuthResponse authResponse = new AuthResponse();

        if (login != null && password != null) {
            User user = userService.findUserByLogin(login);
            if (user != null && user.getPassword().equals(hashPassword(password))) {
                authResponse.setAuthResponseType(AuthResponse.AuthResponseType.AUTH);
                authResponse.setMessage("Успешная авторизация");
            } else {
                authResponse.setAuthResponseType(AuthResponse.AuthResponseType.NO_AUTH);
                authResponse.setMessage("Неверный логин или пароль, попробуйте снова");
            }
        }

        return authResponse;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public AuthResponse register(String login,
                                 String password) {

        AuthResponse authResponse = new AuthResponse();

        if (login != null && password != null) {
            password = hashPassword(password);
            User user = new User(login, password);
            if (userService.findUserByLogin(login) == null) {
                userService.save(user);
                authResponse.setAuthResponseType(AuthResponse.AuthResponseType.REGISTERED);
                authResponse.setMessage("Вы успешно зарегистрированы");
            } else {
                authResponse.setAuthResponseType(AuthResponse.AuthResponseType.NO_REGISTERED);
                authResponse.setMessage("Пользователь с таким логином уже существует, попробуйте изменить логин");
            }
        }

        return authResponse;
    }

    @RequestMapping(value = "/exit", method = RequestMethod.POST)
    public void exit(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    private String hashPassword(String password) {
        return DigestUtils.md5Hex(password);
    }
}
