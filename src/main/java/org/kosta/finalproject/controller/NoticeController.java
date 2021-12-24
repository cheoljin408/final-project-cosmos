package org.kosta.finalproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.kosta.finalproject.config.auth.LoginUser;
import org.kosta.finalproject.config.auth.dto.SessionMember;
import org.kosta.finalproject.model.domain.NoticeFormDTO;
import org.kosta.finalproject.model.domain.StudyMemberDTO;
import org.kosta.finalproject.model.domain.UploadFile;
import org.kosta.finalproject.service.FileStoreService;
import org.kosta.finalproject.service.NoticeService;
import org.kosta.finalproject.service.StudyMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;
    private final FileStoreService fileStoreService;
    private final StudyMemberService studyMemberService;

    @Autowired
    public NoticeController(NoticeService noticeService, FileStoreService fileStoreService, StudyMemberService studyMemberService) {
        this.noticeService = noticeService;
        this.fileStoreService = fileStoreService;
        this.studyMemberService = studyMemberService;
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
                               @LoginUser SessionMember member,
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

        // DB에 저장된 파일 및 이미지들을 불러옴
        Map<String, Object> result = noticeService.getNoticeDetailByNoticeNo(noticeNo);


        List<UploadFile> files = noticeService.findFilesById(noticeNo);
        List<UploadFile> images = noticeService.findImagesById(noticeNo);

        model.addAttribute("images", images);
        model.addAttribute("files", files);
        model.addAttribute("notice", result);

        log.info("notice_content = {}", result.get("NOTICE_CONTENT").toString());

        // 내가 속한 스터디 이름 리스트 가져오기
        Map<String, Object> emailAndStudyNo = new HashMap<String, Object>();
        emailAndStudyNo.put("email", member.getEmail());
        emailAndStudyNo.put("studyNo", studyNo);
        List<Map<String, Object>> studyNameList = studyMemberService.getStudyNameList(emailAndStudyNo);
        log.info("studyNameList: {}", studyNameList);
        model.addAttribute("studyNameList", studyNameList);

        // 해당 스터디에대한 전체 정보 가져오기
        StudyMemberDTO allStudyInfo = studyMemberService.getAllStudyInfo(studyNo);
        log.info("allStudyInfo: {}", allStudyInfo);
        model.addAttribute("allStudyInfo", allStudyInfo);

        return "lms/notice/detail";
    }

    @GetMapping("/list/{studyNo}")
    public String noticeList(@PathVariable int studyNo, Model model,
                          HttpServletResponse response, @LoginUser SessionMember member) {
        model.addAttribute("noticeList", noticeService.getAllNoticeList(studyNo));


        // 내가 속한 스터디 이름 리스트 가져오기
        Map<String, Object> emailAndStudyNo = new HashMap<String, Object>();
        emailAndStudyNo.put("email", member.getEmail());
        emailAndStudyNo.put("studyNo", studyNo);
        List<Map<String, Object>> studyNameList = studyMemberService.getStudyNameList(emailAndStudyNo);
        log.info("studyNameList: {}", studyNameList);
        model.addAttribute("studyNameList", studyNameList);

        // 해당 스터디에대한 전체 정보 가져오기
        StudyMemberDTO allStudyInfo = studyMemberService.getAllStudyInfo(studyNo);
        log.info("allStudyInfo: {}", allStudyInfo);
        model.addAttribute("allStudyInfo", allStudyInfo);

        return "lms/notice/list";
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

    /**
     *   공지사항 업데이트 폼으로 이동
     */
    @PostMapping("/update/form/{studyNo}")
    public String updateNoticeForm(@RequestParam int noticeNo, @PathVariable int studyNo ,
                                   @LoginUser SessionMember member, Model model) {
        model.addAttribute("notice", noticeService.getNoticeDetailByNoticeNo(noticeNo));

        // 내가 속한 스터디 이름 리스트 가져오기
        Map<String, Object> emailAndStudyNo = new HashMap<String, Object>();
        emailAndStudyNo.put("email", member.getEmail());
        emailAndStudyNo.put("studyNo", studyNo);
        List<Map<String, Object>> studyNameList = studyMemberService.getStudyNameList(emailAndStudyNo);
        log.info("studyNameList: {}", studyNameList);
        model.addAttribute("studyNameList", studyNameList);

        // 해당 스터디에대한 전체 정보 가져오기
        StudyMemberDTO allStudyInfo = studyMemberService.getAllStudyInfo(studyNo);
        log.info("allStudyInfo: {}", allStudyInfo);
        model.addAttribute("allStudyInfo", allStudyInfo);

        return "lms/notice/update";
    }

    /**
     * 공지사항 수정
     */
    @PostMapping("/update/{studyNo}")
    @Transactional
    public String update(@PathVariable int studyNo, @ModelAttribute NoticeFormDTO noticeFormDTO,
                         Model model, @LoginUser SessionMember member, @RequestParam int noticeNo,
                         RedirectAttributes redirectAttributes) throws IOException {
        // 1. Content와 Title 정보를 업데이트
        log.info("content, title uploading");
        noticeService.updateNoticeByNoticeNo(noticeFormDTO.getNoticeTitle(),
                                             noticeFormDTO.getNoticeContent(),
                                             noticeNo);
        log.info("delete original images, files");
        // 2. 기존의 이미지와 파일들을 삭제
        noticeService.deleteNoticeFileByNoticeNo(noticeNo);

        // 3. 이미지와 파일들을 재업로드
        List<UploadFile> storeImageFiles = fileStoreService.storeFiles(noticeFormDTO.getImageFiles());
        List<UploadFile> attachFiles = fileStoreService.storeFiles(noticeFormDTO.getAttachFiles());

        // 3.2 ) -> 실직적으로 저장
        noticeService.registerNoticeFiles(noticeNo, noticeFormDTO, attachFiles, storeImageFiles);

        redirectAttributes.addAttribute("noticeNo", noticeNo);
        redirectAttributes.addAttribute("studyNo", studyNo);

        return "redirect:/notice/detail/{studyNo}/{noticeNo}";
    }

    //LMS 사이드바 적용 공지사항 등록페이지
    @GetMapping("/register/{studyNo}")
    public String lmsRegisterNotice(@PathVariable int studyNo, Model model, @LoginUser SessionMember member) {
        model.addAttribute("studyNo", studyNo);

        // 내가 속한 스터디 이름 리스트 가져오기
        Map<String, Object> emailAndStudyNo = new HashMap<String, Object>();
        emailAndStudyNo.put("email", member.getEmail());
        emailAndStudyNo.put("studyNo", studyNo);
        List<Map<String, Object>> studyNameList = studyMemberService.getStudyNameList(emailAndStudyNo);
        log.info("studyNameList: {}", studyNameList);
        model.addAttribute("studyNameList", studyNameList);

        // 해당 스터디에대한 전체 정보 가져오기
        StudyMemberDTO allStudyInfo = studyMemberService.getAllStudyInfo(studyNo);
        log.info("allStudyInfo: {}", allStudyInfo);
        model.addAttribute("allStudyInfo", allStudyInfo);

        return "lms/notice/register";
    }

    @Transactional
    @PostMapping("/register/{studyNo}")
    public String registerNotice(@PathVariable int studyNo, @LoginUser SessionMember member, @ModelAttribute NoticeFormDTO noticeFormDTO, RedirectAttributes redirectAttributes, Model model)  throws IOException {
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

    // 이미지 다운로드, 반환. 보안에 취약해서 보안 로직을 추가해야 함
    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException
    {
        // "file:C:/Users/Yong Lee/test-upload/UUID.확장자"
        // UrlResource: 파일에 직접 접근해서 리소스를 가져오고 스트림이 되어서 반환된다
        return new UrlResource("file:" + fileStoreService.getFullPath(filename));
    }
     // 헤더에 추가해야 할 사항이 있어서 ResponseBody 는 사용하지 않았음
     // 첨부파일 다운로드
    @GetMapping("/attach/{noticeNo}/{index}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable int noticeNo,
                                                   @PathVariable int index) throws MalformedURLException {
        List<UploadFile> files = noticeService.findFilesById(noticeNo); // item을 접근할 수 있는 사용자만 다운로드 권한이 생김

        String uploadFileName = files.get(index).getUploadFileName();
        String storeFileName = files.get(index).getStoreFileName();

        UrlResource resource = new UrlResource("file:" + fileStoreService.getFullPath(storeFileName));

        log.info("uploadFileName={}", uploadFileName);
        log.info("storeFileName={}", storeFileName);

        // 한글이 깨질 수 있어서 encoding
        String encodedUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        // 파일 다운로드를 위한 규약. 사용하지 않을 경우 브라우저에서 다운이 아닌 읽기가 동작
        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }
}