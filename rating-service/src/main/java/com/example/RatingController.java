package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(value = "/rating")
public class RatingController {

    @RequestMapping(value = "/check")
    public String hi() {
        return "------------------------>Checking Rating <----------------------------";
    }

    @GetMapping(value = "/action")
    public String action() {
        return "------------------------>Rating Action <----------------------------";
    }
     @GetMapping(value = "/getmessage/{name}")
    public String getMessage(@PathVariable("name") String name) {

        StringBuilder result = new StringBuilder();

        if (name == null || name.trim().length() == 0) {

            result.append("Please provide a name!");

        } else {

            result.append("Hello " + name);

        }
        return result.toString();
    }
}
