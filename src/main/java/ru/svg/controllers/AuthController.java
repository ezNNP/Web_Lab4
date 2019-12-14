package ru.svg.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.svg.dto.AuthenticationRequestDto;
import ru.svg.entities.User;
import ru.svg.security.jwt.JwtTokenProvider;
import ru.svg.service.UserService;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    @Qualifier("userServiceImpl")
    private final UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) throws UsernameNotFoundException {
        try {
            String login = requestDto.getLogin();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, requestDto.getPassword()));
            User user = userService.findByLogin(login);

            if (user == null) {
                throw new UsernameNotFoundException("User with login " + login + " not found");
            }

            String token = jwtTokenProvider.createToken(login);

            Map<Object, Object> response = new HashMap<>();
            response.put("login", login);
            response.put("status", "OK");
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid login or password");
        }
    }

    @PostMapping("register")
    public ResponseEntity register(@RequestBody AuthenticationRequestDto requestDto) throws BadCredentialsException {
        String login = requestDto.getLogin();
        User duplicate = userService.findByLogin(login);

        if (duplicate != null) {
            throw new BadCredentialsException("Duplicate login");
        }

        User user = new User(requestDto.getLogin(), requestDto.getPassword());
        userService.register(user);

        Map<Object, Object> response = new HashMap<>();
        response.put("login", login);
        response.put("status", "REGISTERED");

        return ResponseEntity.ok(response);
    }
}
