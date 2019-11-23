package ru.svg.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.svg.entities.User;
import ru.svg.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
    public void addUser(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (login != null && password != null) {
            User user = userService.findUserByLogin(login);
            if (user.getPassword().equals(hashPassword(password))) {
                // заебись
            } else {
                // не заебись
            }
        }
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public void register(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (login != null && password != null) {
            password = hashPassword(password);
            User user = new User(login, password);
            userService.save(user);
        }
    }

    private String hashPassword(String password) {
        return password;
    }
}
