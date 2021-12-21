package org.kosta.finalproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/notice")
public class NoticeController {

    @RequestMapping("/detail")
    public String noticeDetail(Model model) {
         NoticeDTO
         return "/notice/notice-detail";
    }
    @PostMapping("/post")
    public String postTest(String text, Model model){
        System.out.println("text = " + text );
        model.addAttribute("data", text);
        return "/notice/notice-result";
    }
}