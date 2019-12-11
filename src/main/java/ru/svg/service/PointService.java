package ru.svg.service;


import ru.svg.entities.Point;

import java.util.Collection;

public interface PointService {
    Collection<Point> add(Point point);

    Collection<Point> findAll();
}
