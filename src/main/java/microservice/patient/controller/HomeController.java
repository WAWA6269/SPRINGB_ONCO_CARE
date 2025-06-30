package microservice.patient.controller;

import org.springframework.web.bind.annotation.*;

//@controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "Patient Service is running!";
    }
}
