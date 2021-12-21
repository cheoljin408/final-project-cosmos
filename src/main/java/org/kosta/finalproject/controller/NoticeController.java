package org.kosta.finalproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NoticeController {

    @GetMapping("/register-notice")
    public String testRegisterNotice() {
        return "/notice/sidebar-test";
    }

    @GetMapping("/sidebar")
    public String sidebar() {
        return "fragments/sidebar";
    }

    @GetMapping("/sidebar2")
    public String sidebar2() {
        return "notice/sidebar-test2";
    }

    //공지사항 등록페이지(Test)
    @GetMapping("/registerNotice")
    public String registerNotice() {
        return "notice/register-notice";
    }

    //lms 사이드바 적용 공지사항 등록페이지
    @GetMapping("/lmsRegisterNotice")
    public String lmsRegisterNotice() {
        return "lms/lms-register-notice";
    }

    @GetMapping("/noticelist")
    public String noticelist() {
        return "noticelist";
    }


}
