package ru.svg.controllers;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.svg.entities.User;
import ru.svg.responces.AuthResponse;
import ru.svg.service.UserService;

@RestController
@RequestMapping(value = "/auth", method = RequestMethod.POST)
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin
    @RequestMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        AuthResponse authResponse = new AuthResponse();
        if (user.getLogin() != null && user.getPassword() != null) {
            User db_user = userService.findUserByLogin(user.getLogin());
            if (db_user != null && db_user.getPassword().equals(hashPassword(user.getPassword()))) {
                logger.info("User with login \"{}\" is logged in", user.getLogin());
                authResponse.setAuthResponseType(AuthResponse.AuthResponseType.AUTH);
                authResponse.setMessage("Успешная авторизация");
            } else {
                logger.info("User with login \"{}\" isn't logged in", user.getLogin());
                authResponse.setAuthResponseType(AuthResponse.AuthResponseType.NO_AUTH);
                authResponse.setMessage("Неверный логин или пароль, попробуйте снова");
            }
        }

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(value = "/register")
    public ResponseEntity<?> register(@RequestBody User newUser) {
        AuthResponse authResponse = new AuthResponse();

        if (newUser.getLogin() != null && newUser.getPassword() != null) {
            newUser.setPassword(hashPassword(newUser.getPassword()));
            if (userService.findUserByLogin(newUser.getLogin()) == null) {
                userService.save(newUser);
                logger.info("User with login \"{}\" is registered", newUser.getLogin());
                authResponse.setAuthResponseType(AuthResponse.AuthResponseType.REGISTERED);
                authResponse.setMessage("Вы успешно зарегистрированы");
            } else {
                logger.warn("User with login \"{}\" isn't registered. Duplicate username.", newUser.getLogin());
                authResponse.setAuthResponseType(AuthResponse.AuthResponseType.NO_REGISTERED);
                authResponse.setMessage("Пользователь с таким логином уже существует, попробуйте изменить логин");
            }
        }

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    private String hashPassword(String password) {
        return DigestUtils.md5Hex(password);
    }
}
