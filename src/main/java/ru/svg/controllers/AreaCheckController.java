package ru.svg.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.svg.entities.Point;
import ru.svg.service.PointService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/points", method = RequestMethod.POST)
public class AreaCheckController {


    @Autowired
    @Qualifier("pointServiceImpl")
    private PointService pointService;

    @PostMapping(value = "/add_point")
    public ResponseEntity addPoint(@RequestBody Point point) {
        point.setHit(checkIn(point.getX(), point.getY(), point.getR()));
        point.setCorrect(true);
        Collection<Point> points = pointService.add(point);
        Map<Object, Object> response = new HashMap<>();
        response.put("points", points);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/get_points")
    public ResponseEntity getPoints() {
        Collection<Point> points = pointService.findAll();
        Map<Object, Object> response = new HashMap<>();
        response.put("points", points);
        return ResponseEntity.ok(response);
    }

    private boolean checkIn(double x, double y, double r) {
        if (x >= 0 && y >= 0) {
            return (r*r) >= (x*x) + (y*y);
        } else if (x >= 0 && y <= 0) {
            return y >= x - r/2;
        } else if (x <= 0 && y <= 0) {
            return (x > -r) && (y > -r/2);
        }
        return false;
    }
}
