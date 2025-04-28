package de.fi.webapp.presention.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")

public class DemoController {

    @GetMapping(path = "/gruss", produces = "text/plain")
    public String gruss() {
        return "Hallo Rest";
    }
}
