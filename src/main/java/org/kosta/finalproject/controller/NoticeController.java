package org.kosta.finalproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NoticeController {

    //LMS 사이드바 적용 공지사항 등록페이지
    @GetMapping("/lmsRegisterNotice")
    public String lmsRegisterNotice() {
        return "lms/lms-register-notice";
    }
}
