package ru.svg.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.svg.entities.Point;
import ru.svg.responces.AreaCheckResponse;
import ru.svg.service.PointService;

@RestController
public class AreaCheckController {
    private PointService pointService;

    @Autowired
    public AreaCheckController(PointService pointService) {
        this.pointService = pointService;
    }

    @RequestMapping(value = "/add_point", method = RequestMethod.POST)
    public AreaCheckResponse addPoint(@RequestParam(value = "x") String str_x,
                                      @RequestParam(value = "y") String str_y,
                                      @RequestParam(value = "r") String str_r) {
        Point point = new Point();
        try {
            double x = Double.parseDouble(str_x);
            double y = Double.parseDouble(str_y);
            double r = Double.parseDouble(str_r);
            if (r > 0) {
                point.setX(x);
                point.setY(y);
                point.setR(r);
                point.setIn(checkIn(x, y, r));
                point.setCorrect(true);
            } else {
                point.setCorrect(false);
            }

        } catch (Exception e) {
            point.setCorrect(false);
        }
        pointService.save(point);
        return new AreaCheckResponse(pointService.findAllOrderById());
    }

    private boolean checkIn(double x, double y, double r) {
        if (x >= 0 && y >= 0) {
            return (r*r) <= (x*x) + (y*y);
        } else if (x >= 0 && y <= 0) {
            return y >= x - r/2;
        } else if (x <= 0 && y <= 0) {
            return (x > -r) && (y > -r/2);
        }
        return false;
    }
}
