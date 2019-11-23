package ru.svg.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.svg.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCrudRepository extends CrudRepository<User, Long> {
    @Query("select c from User c where c.login = :login")
    User findUserByLogin(@Param("login") String login);
}
