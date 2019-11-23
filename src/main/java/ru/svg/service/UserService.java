package ru.svg.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import ru.svg.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.svg.repositories.UserCrudRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@EnableJpaRepositories(basePackageClasses = {UserCrudRepository.class})
public class UserService {
    
    private UserCrudRepository userCrudRepository;

    @Autowired
    public UserService(UserCrudRepository userCrudRepository) {
        this.userCrudRepository = userCrudRepository;
    }

    public long count() {
        return userCrudRepository.count();
    }

    public void delete(User user) {
        userCrudRepository.delete(user);
    }

    public void deleteAll() {
        userCrudRepository.deleteAll();
    }

    public void deleteAll(Iterable<User> collection) {
        userCrudRepository.deleteAll(collection);
    }

    public void deleteById(Long id) {
        userCrudRepository.deleteById(id);
    }

    public boolean existById(Long id) {
        return userCrudRepository.existsById(id);
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        Iterable<User> iterable = userCrudRepository.findAll();
        iterable.forEach(users::add);

        return users;
    }

    public void save(User user) {
        userCrudRepository.save(user);
    }

    public void saveAll(Iterable<User> users) {
        userCrudRepository.saveAll(users);
    }

    public UserCrudRepository getRepository() {
        return userCrudRepository;
    }

    public User findUserByLogin(String login) {
        return userCrudRepository.findUserByLogin(login);
    }

    public void setRepository(UserCrudRepository userCrudRepository) {
        this.userCrudRepository = userCrudRepository;
    }
}
