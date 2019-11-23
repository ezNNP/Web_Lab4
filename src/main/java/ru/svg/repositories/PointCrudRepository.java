package ru.svg.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.svg.entities.Point;

@Repository
public interface PointCrudRepository extends CrudRepository<Point, Long> {
    @Query("select c from Point c order by c.id DESC")
    Iterable<Point> findAllOrderById();
}
