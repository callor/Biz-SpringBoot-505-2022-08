package com.callor.data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // @RequestMapping(value={"","/"}, RequestMethod=GET)
    @GetMapping(value = {"","/"})
    public String home() {
        return "home";
    }

}
