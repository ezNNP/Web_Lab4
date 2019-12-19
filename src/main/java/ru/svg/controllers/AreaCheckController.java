package ru.svg.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.svg.entities.Point;
import ru.svg.entities.User;
import ru.svg.service.PointService;
import ru.svg.service.UserService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/points", method = RequestMethod.POST)
public class AreaCheckController {


    @Qualifier("pointServiceImpl")
    private PointService pointService;
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Autowired
    public AreaCheckController(PointService pointService, UserService userService) {
        this.pointService = pointService;
        this.userService = userService;
    }

    @PostMapping(value = "/add_point")
    @CrossOrigin
    public ResponseEntity<?> addPoint(@RequestBody Point point) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        User user = userService.findByLogin(login);
        point.setHit(checkIn(point.getX(), point.getY(), point.getR()));
        point.setCorrect(true);
        point.setOwner(user);
        pointService.add(point);
        Collection<Point> points = pointService.findAllForUser(user);
        Map<Object, Object> response = new HashMap<>();
        response.put("points", points);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/get_user_points")
    @CrossOrigin
    public ResponseEntity<?> getUserPoints() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        User user = userService.findByLogin(login);
        Collection<Point> points = pointService.findAllForUser(user);
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
