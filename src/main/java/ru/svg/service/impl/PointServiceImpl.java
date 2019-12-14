package ru.svg.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.svg.entities.Point;
import ru.svg.entities.User;
import ru.svg.repositories.PointRepository;
import ru.svg.service.PointService;

import java.util.Collection;

@Slf4j
@Component("pointServiceImpl")
public class PointServiceImpl implements PointService {

    private final PointRepository pointRepository;

    @Autowired
    public PointServiceImpl(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    @Override
    public void add(Point point) {
        pointRepository.save(point);
        log.info("Point {} is successfully saved in database", point);
    }

    @Override
    public Collection<Point> findAll() {
        log.info("Get points");
        return pointRepository.findAllOrderById();
    }

    @Override
    public Collection<Point> findAllForUser(User owner) {
        log.info("Get points for user {}", owner.getLogin());
        Collection<Point> points = pointRepository.findAllByOwnerOrderById(owner);
        for (Point point : points) {
            point.setOwner(null);
        }
        return points;
    }
}
