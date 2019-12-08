package ru.svg.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.XmlWebApplicationContext;
import ru.svg.entities.User;
import ru.svg.responces.AuthResponse;
import ru.svg.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private GsonBuilder gsonBuilder;

    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
    public void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        XmlWebApplicationContext context = new XmlWebApplicationContext();
        AuthResponse authResponse = context.getBean("authResponse", AuthResponse.class);

        String json = request.getParameter("json");
        Gson gson = gsonBuilder.create();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

        String login = jsonObject.get("login").getAsString();
        String password = jsonObject.get("password").getAsString();

        HttpSession session = request.getSession();
        if (login != null && password != null) {
            User user = userService.findUserByLogin(login);
            if (user != null && user.getPassword().equals(hashPassword(password))) {
                session.setAttribute("access", "true");
                authResponse.setAuthResponseType(AuthResponse.AuthResponseType.AUTH);
                authResponse.setMessage("Успешная авторизация");
            } else {
                authResponse.setAuthResponseType(AuthResponse.AuthResponseType.NO_AUTH);
                authResponse.setMessage("Неверный логин или пароль, попробуйте снова");
            }
        }

        String responseString = gson.toJson(authResponse);
        System.out.println(responseString);
        response.getWriter().print(responseString);
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        XmlWebApplicationContext context = new XmlWebApplicationContext();
        AuthResponse authResponse = context.getBean("authResponse", AuthResponse.class);

        String json = request.getParameter("json");
        Gson gson = gsonBuilder.create();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

        String login = jsonObject.get("login").getAsString();
        String password = jsonObject.get("password").getAsString();

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

        String responseString = gson.toJson(authResponse);
        System.out.println(responseString);
        response.getWriter().print(responseString);
    }

    @RequestMapping(value = "/exit", method = RequestMethod.POST)
    public void exit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");XmlWebApplicationContext context = new XmlWebApplicationContext();
        AuthResponse authResponse = context.getBean("authResponse", AuthResponse.class);

        String json = request.getParameter("json");
        Gson gson = gsonBuilder.create();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

        String exit = jsonObject.get("exit").getAsString();
        if (exit.equals("true")) {
            request.getSession().removeAttribute("access");
            authResponse.setAuthResponseType(AuthResponse.AuthResponseType.EXIT);
            authResponse.setMessage("Вы успешно вышли");
        } else {
            authResponse.setAuthResponseType(AuthResponse.AuthResponseType.NO_EXIT);
            authResponse.setMessage("Неверный JSON запрос");
        }

        String responseString = gson.toJson(authResponse);
        System.out.println(responseString);
        response.getWriter().print(responseString);
    }

    private String hashPassword(String password) {
        return DigestUtils.md5Hex(password);
    }
}
