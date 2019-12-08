package ru.svg.responces;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.svg.entities.Point;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Component
@Scope(scopeName = "prototype")
public class AreaCheckResponse {
    private List<Point> points;
}
