package ru.svg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"ru.svg"})
public class WebLabApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebLabApplication.class, args);
    }
}
