package org.kosta.finalproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.kosta.finalproject.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class NoticeController {

    private final NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/notice/list/{studyNo}")
    public String comment(@PathVariable int studyNo, Model model) {
        model.addAttribute("noticeList", noticeService.getAllNoticeList(studyNo));
        return "notice-list";
    }

}
