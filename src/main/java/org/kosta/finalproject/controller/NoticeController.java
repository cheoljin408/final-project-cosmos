package org.kosta.finalproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.kosta.finalproject.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @Autowired
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }


    /**
     *  공지사항 상세보기
     * 
     *  공지사항 리스트에서 해당 공지사항 상세보기를 누르면 해당 공지사항에 대한 정보를 출력한다.

     */
    @RequestMapping("/detail/{noticeNo}")
    public String noticeDetail(Model model,
                               @PathVariable int noticeNo) {
        model.addAttribute("notice", noticeService.getNoticeDetailByNoticeNo(noticeNo));
        return "/notice/notice-detail-study";
    }

    /**
     * ** 테스트용 코드 ** 
     * 
     *  텍스트 에디터폼에서 작성한 후 해당 editor애 생성된 html 태그들을 
     *  올바르게 전송이 되는지 검사
     * 
     */
    @PostMapping("/post")
    public String postTest(String text, Model model){
        System.out.println("text = " + text );
        model.addAttribute("data", text);
        return "/notice/notice-result";
    }

    @GetMapping("/list/{studyNo}")
    public String comment(@PathVariable int studyNo, Model model,
                          HttpServletResponse response) {
        /*  쿠키를 이용한 조회수 중복 체크  */
        Cookie cookie = new Cookie("view", null);
        cookie.setComment("checkDupHits");
        cookie.setMaxAge(60*60*24);	// 유효기간을 24시간으로 한정
        response.addCookie(cookie);

        model.addAttribute("noticeList", noticeService.getAllNoticeList(studyNo));
        return "/notice/notice-list";
    }
}