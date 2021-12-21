package org.kosta.finalproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.kosta.finalproject.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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
     * 공지사항 상세보기
     * <p>
     * 공지사항 리스트에서 해당 공지사항 상세보기를 누르면 해당 공지사항에 대한 정보를 출력한다.
     */
    @RequestMapping("/detail/{studyNo}/{noticeNo}")
    public String noticeDetail(Model model,
                               HttpServletRequest request,
                               HttpServletResponse response,
                               @PathVariable int noticeNo,
                               @PathVariable int studyNo) {


        Cookie newCookie = null;
        Cookie[] nowCookies = request.getCookies();
        if (nowCookies != null) {
            for (Cookie cookie : nowCookies) {
                if (cookie.getName().equals("postView")) {
                    newCookie = cookie;
                }
            }
        }
        if (newCookie != null) {
            if (!newCookie.getValue().contains("[" + studyNo + "" + noticeNo + "]")) {
                noticeService.updateHits(studyNo, noticeNo);
                newCookie.setValue(newCookie.getValue() + "_[" + studyNo + "" + noticeNo + "]");
                newCookie.setPath("/");
                newCookie.setMaxAge(60 * 60 * 24);
                response.addCookie(newCookie);
            }
        } else {
            noticeService.updateHits(studyNo, noticeNo);
            Cookie cookie = new Cookie("postView", "[" + studyNo + "" + noticeNo + "]");
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60 * 24);
            response.addCookie(cookie);
        }
        model.addAttribute("notice", noticeService.getNoticeDetailByNoticeNo(noticeNo));
        return "/notice/notice-detail-study";
    }

    /**
     * ** 테스트용 코드 **
     * <p>
     * 텍스트 에디터폼에서 작성한 후 해당 editor애 생성된 html 태그들을
     * 올바르게 전송이 되는지 검사
     */
    @PostMapping("/post")
    public String postTest(String text, Model model) {
        model.addAttribute("data", text);
        return "/notice/notice-result";
    }

    @GetMapping("/list/{studyNo}")
    public String comment(@PathVariable int studyNo, Model model,
                          HttpServletResponse response) {
        model.addAttribute("noticeList", noticeService.getAllNoticeList(studyNo));
        return "/notice/notice-list";
    }

    /**
     * 공지사항 삭제
     */
    @PostMapping("/delete")
    @ResponseBody
    public String deleteNotice(@RequestParam int noticeNo) {
        log.info("deleteNotice() starrr..");
        noticeService.deleteNotice(noticeNo);
        return null;
    }

    @PostMapping("/update")
    public String updateNotice(@RequestParam int noticeNo,
                               Model model){
        model.addAttribute("notice", noticeService.getNoticeDetailByNoticeNo(noticeNo));
        return "notice/notice-update";
    }
}