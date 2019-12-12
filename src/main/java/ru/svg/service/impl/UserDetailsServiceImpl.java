package ru.svg.service.impl;

import com.sun.security.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.svg.entities.User;
import ru.svg.repositories.UserRepository;
import ru.svg.security.jwt.JwtUserFactory;

import java.util.NoSuchElementException;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {


    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByLogin(login);
            return JwtUserFactory.create(user);
        } catch (NoSuchElementException e) {
            throw new UsernameNotFoundException("User " + login + " not found.", e);
        }
    }

}
