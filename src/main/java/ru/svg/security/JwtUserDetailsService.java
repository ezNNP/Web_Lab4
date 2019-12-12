package ru.svg.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.svg.entities.User;
import ru.svg.security.jwt.JwtUser;
import ru.svg.security.jwt.JwtUserFactory;
import ru.svg.service.UserService;

@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.findByLogin(s);
        if (user == null) {
            log.error("User with login {} not found", s);
            throw new UsernameNotFoundException("User with login " + s + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("User {} successfully loaded", user);
        return jwtUser;
    }
}
