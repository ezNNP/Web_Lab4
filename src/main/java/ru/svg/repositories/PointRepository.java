package ru.svg.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.svg.entities.Point;

import java.util.Collection;

public interface PointRepository extends JpaRepository<Point, Long> {
    @Query("select c from Point c order by c.id DESC")
    Collection<Point> findAllOrderById();
}
