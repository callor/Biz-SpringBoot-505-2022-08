package com.callor.book.controller;

import com.callor.book.model.UserVO;
import com.callor.book.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j

@Controller
@RequestMapping(value="/user")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value="/login",method = RequestMethod.GET)
    public String login() {
        return "user/login";

    }

    @RequestMapping(value="/join",method=RequestMethod.GET)
    public String join() {
        return "user/join";
    }

    @RequestMapping(value="/join",method = RequestMethod.POST)
    public String join(UserVO userVO) {

        log.debug("회원가입 정보 {}",userVO);
        userService.join(userVO);

        return "redirect:/";
    }


}
