package ru.svg.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.svg.service.PointService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class AreaCheckController {
    @Autowired
    private PointService pointService;

    @Autowired
    private GsonBuilder gsonBuilder;

    @RequestMapping(value = "/add_point", method = RequestMethod.POST)
    public void addPoint(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        String json = request.getParameter("json");
        Gson gson = gsonBuilder.create();
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        double x = jsonObject.get("x").getAsDouble();
        double y = jsonObject.get("y").getAsDouble();
        double r = jsonObject.get("r").getAsDouble();
        int var = jsonObject.get("var").getAsInt();
    }
}
