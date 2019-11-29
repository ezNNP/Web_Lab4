package ru.svg.responces;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.svg.entities.Point;

import java.util.List;

@Component
@Scope(scopeName = "prototype")
public class AreaCheckResponse {
    private List<Point> points;

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
