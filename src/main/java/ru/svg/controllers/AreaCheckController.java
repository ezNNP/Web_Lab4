package ru.svg.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.svg.entities.Point;
import ru.svg.service.PointService;

@RestController
public class AreaCheckController {
    private static final Logger logger = LoggerFactory.getLogger(AreaCheckController.class);

    private PointService pointService;

    @Autowired
    public AreaCheckController(PointService pointService) {
        this.pointService = pointService;
    }

    @PostMapping(value = "/add_point")
    public ResponseEntity<?> addPoint(@RequestBody Point point) {
        point.setHit(checkIn(point.getX(), point.getY(), point.getR()));
        point.setCorrect(true);
        pointService.save(point);
        logger.info("Point {} is succesfully saved", point);
        return new ResponseEntity<>(pointService.findAllOrderById(), HttpStatus.OK);
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
