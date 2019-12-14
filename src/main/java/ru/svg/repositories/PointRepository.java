package ru.svg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.svg.entities.Point;
import ru.svg.entities.User;

import java.util.Collection;

public interface PointRepository extends JpaRepository<Point, Long> {
    @Query("select c from Point AS c order by c.id DESC")
    Collection<Point> findAllOrderById();

    @Query("select c from Point AS c where c.owner = :owner order by c.id DESC")
    Collection<Point> findAllByOwnerOrderById(@Param("owner")User owner);
}
