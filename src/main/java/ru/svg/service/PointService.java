package ru.svg.service;


import ru.svg.entities.Point;
import ru.svg.entities.User;

import java.util.Collection;

public interface PointService {
    void add(Point point);

    Collection<Point> findAll();

    Collection<Point> findAllForUser(User user);
}
