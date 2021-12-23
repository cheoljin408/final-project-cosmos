package org.kosta.finalproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.kosta.finalproject.config.auth.LoginUser;
import org.kosta.finalproject.config.auth.dto.SessionMember;
import org.kosta.finalproject.model.domain.NoticeFormDTO;
import org.kosta.finalproject.model.domain.StudyMemberDTO;
import org.kosta.finalproject.model.domain.UploadFile;
import org.kosta.finalproject.service.FileStoreService;
import org.kosta.finalproject.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;
    private final FileStoreService fileStoreService;

    @Autowired
    public NoticeController(NoticeService noticeService, FileStoreService fileStoreService) {
        this.noticeService = noticeService;
        this.fileStoreService = fileStoreService;
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
        return "/lms/notice/detail";
    }

    @GetMapping("/list/{studyNo}")
    public String noticeList(@PathVariable int studyNo, Model model,
                          HttpServletResponse response) {
        model.addAttribute("noticeList", noticeService.getAllNoticeList(studyNo));
        return "/lms/notice/list";
    }

    //LMS 사이드바 적용 공지사항 등록페이지
    @GetMapping("/lmsRegisterNotice/{studyNo}")
    public String lmsRegisterNotice(@PathVariable int studyNo, @ModelAttribute NoticeFormDTO noticeFormDTO, Model model) {
        model.addAttribute("studyNo", studyNo);
        return "lms/notice/register";
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
                               Model model) {
        model.addAttribute("notice", noticeService.getNoticeDetailByNoticeNo(noticeNo));
        return "notice/notice-update";
    }

    @Transactional
    @PostMapping("/lmsRegisterNotice/{studyNo}")
    public String registerNotice(@PathVariable int studyNo, @LoginUser SessionMember member, @ModelAttribute NoticeFormDTO noticeFormDTO, RedirectAttributes redirectAttributes)  throws IOException {
        log.info("noticeFormDTO: {}", noticeFormDTO);
        // 파일에 저장
        // MultipartFile attachFile = form.getAttachFile();
        // UploadFile attachFile = fileStore.storeFile(attachFile);

        log.info("attach:{}", noticeFormDTO.getAttachFiles());
        List<UploadFile> attachFiles = fileStoreService.storeFiles(noticeFormDTO.getAttachFiles());
        log.info("NoticeController, registerNotice, attachFiles: {}", attachFiles);
        // List<MultipartFile> imageFiles = form.getImageFiles();
        // List<UploadFile> storeImageFiles = fileStore.storeFiles(form.getImageFiles());
        List<UploadFile> storeImageFiles = fileStoreService.storeFiles(noticeFormDTO.getImageFiles());
        log.info("NoticeController, registerNotice, storeImageFiles: {}", storeImageFiles);

        //데이터베이스에 저장
        int noticeNo = noticeService.registerNotice(studyNo, member.getEmail(), noticeFormDTO, attachFiles, storeImageFiles);
        // noticeNo = noticeService.registerNotice(studyNo, member.getEmail(), noticeFormDTO, attachFiles, storeImageFiles);
        log.info("noticeNo: {}", noticeNo);

        redirectAttributes.addAttribute("noticeNo", noticeNo);
        redirectAttributes.addAttribute("studyNo", studyNo);

        return "redirect:/notice/detail/{studyNo}/{noticeNo}";
    }

}
