package ru.svg.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.svg.entities.User;
import ru.svg.repositories.UserRepository;
import ru.svg.service.UserService;

import java.util.List;

@Slf4j
@Component("userServiceImpl")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User registeredUser = userRepository.save(user);

        log.info("User {} successfully registered", registeredUser);
        return registeredUser;
    }

    @Override
    public List<User> getAll() {

        return null;
    }

    @Override
    public User findByLogin(String login) {
        User user = userRepository.findByLogin(login);
        if (user == null) {
            log.info("User {} wasn't found on database", login);
        } else {
            log.info("User {} successfully founded", login);
        }
        return user;
    }
}
