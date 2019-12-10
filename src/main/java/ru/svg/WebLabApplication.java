package ru.svg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication(scanBasePackages = {"ru.svg"})
public class WebLabApplication {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(WebLabApplication.class, args);
    }
}
