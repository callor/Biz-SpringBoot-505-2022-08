package com.callor.book.controller;

import com.callor.book.model.BookVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    @RequestMapping(value="",method= RequestMethod.GET)
    public String home() {
        return "home";
    }

    @RequestMapping(value="",method = RequestMethod.POST)
    public String home(BookVO bookVo) {

        return "redirect:/";
    }
}
