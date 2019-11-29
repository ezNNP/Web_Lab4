package ru.svg.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.XmlWebApplicationContext;
import ru.svg.entities.Point;
import ru.svg.responces.AreaCheckResponse;
import ru.svg.service.PointService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class AreaCheckController {
    @Autowired
    private PointService pointService;

    @Autowired
    private GsonBuilder gsonBuilder;

    @RequestMapping(value = "/add_point", method = RequestMethod.POST)
    public void addPoint(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        XmlWebApplicationContext context = new XmlWebApplicationContext();
        AreaCheckResponse areaCheckResponse = context.getBean("areaCheckResponse", AreaCheckResponse.class);
        String json = request.getParameter("json");
        Gson gson = gsonBuilder.create();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        double x = jsonObject.get("x").getAsDouble();
        double y = jsonObject.get("y").getAsDouble();
        double r = jsonObject.get("r").getAsDouble();
        int var = jsonObject.get("var").getAsInt();
        Point point = new Point();
        point.setX(x);
        point.setY(y);
        point.setR(r);
        point.setVar(var);
        point.setCorrect(checkCorrectByVariant(x, y, r, var));
        point.setIn(checkInByVariant(x, y, r, var));
        pointService.save(point);

        areaCheckResponse.setPoints(pointService.findAllByVarOrderById(var));

        String responseString = gson.toJson(areaCheckResponse);
        System.out.println(responseString);
        response.getWriter().print(responseString);
    }

    private boolean checkCorrectByVariant(double x, double y, double r, int var) {
        switch (var) {
            case 44:

        }
        return false;
    }

    private boolean checkInByVariant(double x, double y, double r, int var) {
        switch (var) {
            case 44:

        }
        return false;
    }
}
