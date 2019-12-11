package ru.svg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.svg.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);
}
