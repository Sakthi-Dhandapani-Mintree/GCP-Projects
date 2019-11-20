package example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
