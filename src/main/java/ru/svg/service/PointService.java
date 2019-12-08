package ru.svg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import ru.svg.entities.Point;
import ru.svg.repositories.PointCrudRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@EnableJpaRepositories(basePackageClasses = {PointCrudRepository.class})
public class PointService {

    private PointCrudRepository pointCrudRepository;

    @Autowired
    public PointService(PointCrudRepository pointCrudRepository) {
        this.pointCrudRepository = pointCrudRepository;
    }

    public long count() {
        return pointCrudRepository.count();
    }

    public void delete(Point point) {
        pointCrudRepository.delete(point);
    }

    public void deleteAll() {
        pointCrudRepository.deleteAll();
    }

    public void deleteAll(Iterable<Point> collection) {
        pointCrudRepository.deleteAll(collection);
    }

    public void deleteById(Long id) {
        pointCrudRepository.deleteById(id);
    }

    public boolean existById(Long id) {
        return pointCrudRepository.existsById(id);
    }

    public List<Point> findAll() {
        List<Point> points = new ArrayList<>();

        Iterable<Point> iterable = pointCrudRepository.findAll();
        iterable.forEach(points::add);

        return points;
    }

    public void save(Point point) {
        pointCrudRepository.save(point);
    }

    public void saveAll(Iterable<Point> points) {
        pointCrudRepository.saveAll(points);
    }

    public List<Point> findAllOrderById() {
        List<Point> points = new ArrayList<>();

        Iterable<Point> iterable = pointCrudRepository.findAllOrderById();
        iterable.forEach(points::add);

        return points;
    }
}
