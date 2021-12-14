package org.kosta.finalproject.controller;

import lombok.RequiredArgsConstructor;
import org.kosta.finalproject.config.auth.LoginUser;
import org.kosta.finalproject.config.auth.dto.SessionMember;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final HttpSession httpSession;

    @GetMapping("/")
    public String home(Model model, @LoginUser SessionMember member) {

        if(member != null) {
            model.addAttribute("member", member);
        }

        return "index";
    }
}
