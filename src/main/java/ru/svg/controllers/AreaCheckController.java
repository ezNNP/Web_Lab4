package ru.svg.controllers;

import com.google.gson.GsonBuilder;
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
    }
}
