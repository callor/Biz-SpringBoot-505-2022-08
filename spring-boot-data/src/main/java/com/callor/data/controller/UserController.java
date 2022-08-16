package com.callor.data.controller;

import com.callor.data.model.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
@RequestMapping(value = "/users")
public class UserController {

    @RequestMapping(value="/join",method = RequestMethod.GET)
    public String join(@ModelAttribute("userVO") UserVO userVO, Model model) {

        /*
         input.html 파일의 form tag 에
         th:object 항목이 설정 되어 있기 때문에 빈(blank) VO 를
         생성하여 Model 담아 Template(view)에 보내주기
         */
        model.addAttribute("user",userVO);
        return "users/input";
    }

    @RequestMapping(value="/join",method = RequestMethod.POST)
    public String join(UserVO userVO) {
        log.debug("받은 데이터 {}", userVO);
        return "redirect:/";
    }
}
