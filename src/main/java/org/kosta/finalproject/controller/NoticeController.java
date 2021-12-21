package org.kosta.finalproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.kosta.finalproject.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @RequestMapping("/detail")
    public String noticeDetail(Model model) {
         return "/notice/notice-detail";
    }
    @PostMapping("/post")
    public String postTest(String text, Model model){
        System.out.println("text = " + text );
        model.addAttribute("data", text);
        return "/notice/notice-result";
    }

    @GetMapping("/list/{studyNo}")
    public String comment(@PathVariable int studyNo, Model model) {
        model.addAttribute("noticeList", noticeService.getAllNoticeList(studyNo));
        return "notice-list";
    }
}