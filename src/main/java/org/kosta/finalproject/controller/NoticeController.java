package org.kosta.finalproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.kosta.finalproject.config.auth.LoginUser;
import org.kosta.finalproject.config.auth.dto.SessionMember;
import org.kosta.finalproject.model.domain.NoticeDTO;
import org.kosta.finalproject.model.domain.NoticeFormDTO;
import org.kosta.finalproject.model.domain.UploadFile;
import org.kosta.finalproject.service.FileStoreService;
import org.kosta.finalproject.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
public class NoticeController {

    private final NoticeService noticeService;
    private final FileStoreService fileStoreService;

    @Autowired
    public NoticeController(NoticeService noticeService, FileStoreService fileStoreService) {
        this.noticeService = noticeService;
        this.fileStoreService = fileStoreService;
    }

    @GetMapping("/notice/list/{studyNo}")
    public String comment(@PathVariable int studyNo, Model model) {
        model.addAttribute("noticeList", noticeService.getAllNoticeList(studyNo));
        return "notice-list";
    }

    //LMS 사이드바 적용 공지사항 등록페이지
    @GetMapping("/lmsRegisterNotice/{studyNo}")
    public String lmsRegisterNotice(@PathVariable int studyNo, @ModelAttribute NoticeFormDTO noticeFormDTO, Model model) {
        model.addAttribute("studyNo", studyNo);
        return "lms/lms-register-notice";
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
