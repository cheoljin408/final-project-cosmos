package org.kosta.finalproject.controller;

import org.kosta.finalproject.config.auth.LoginUser;
import org.kosta.finalproject.config.auth.dto.SessionMember;
import org.kosta.finalproject.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ApplyController {

    private final ApplyService applyService;

    @Autowired
    public ApplyController(ApplyService applyService) {
        this.applyService = applyService;
    }

    @GetMapping("/alarm")
    public String alarm(Model model) {
        System.out.println(applyService.alarm());
        return "apply-alarm";
    }

    @ResponseBody
    @PostMapping("applyStudy")
    public int applyStudy(@LoginUser SessionMember member, @RequestBody HashMap<String, String> jsonData, Model model){
        if(member != null) {
            model.addAttribute("member", member);
            model.addAttribute("picture", member.getPicture());
        }
        return applyService.registerApplyStudy(jsonData); //성공했으면 return 1, 이미 등록되어있어서 등록이 안됏다면 return 0
    }

}
