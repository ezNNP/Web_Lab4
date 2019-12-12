package ru.svg.security.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.svg.entities.User;

import java.util.ArrayList;
import java.util.List;

public final class JwtUserFactory {
    public JwtUserFactory() {}

    public static JwtUser create(User from) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        return new JwtUser(from.getLogin(), from.getPassword(), authorities);
    }
}
