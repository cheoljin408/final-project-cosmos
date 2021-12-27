package org.kosta.finalproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.kosta.finalproject.config.auth.LoginUser;
import org.kosta.finalproject.config.auth.dto.SessionMember;
import org.kosta.finalproject.model.domain.StudyMemberDTO;
import org.kosta.finalproject.model.domain.UploadFile;
import org.kosta.finalproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/task")
public class TaskController_Joon {

    private final FileStoreService fileStoreService;
    private final StudyMemberService studyMemberService;
    private final StudyService studyService;
    private final TaskService taskService;


    @Autowired
    public TaskController_Joon(FileStoreService fileStoreService, StudyMemberService studyMemberService, StudyService studyService, TaskService taskService) {
        this.fileStoreService = fileStoreService;
        this.studyMemberService = studyMemberService;
        this.studyService = studyService;
        this.taskService = taskService;
    }

    @RequestMapping("/detail/{studyNo}/{taskNo}")
    public String taskDetail(Model model,
                               HttpServletRequest request,
                               HttpServletResponse response,
                               @LoginUser SessionMember member,
                               @PathVariable int taskNo,
                               @PathVariable int studyNo) {

        // 내가 속한 스터디 이름 리스트 가져오기
        Map<String, Object> emailAndStudyNo = new HashMap<String, Object>();
        emailAndStudyNo.put("email", member.getEmail());
        emailAndStudyNo.put("studyNo", studyNo);
        List<Map<String, Object>> studyNameList = studyMemberService.getStudyNameList(emailAndStudyNo);
        model.addAttribute("studyNameList", studyNameList);

        // 해당 스터디에대한 전체 정보 가져오기
        StudyMemberDTO allStudyInfo = studyMemberService.getAllStudyInfo(studyNo);
        model.addAttribute("allStudyInfo", allStudyInfo);
        /* ******************  중복 코드 ****************** */

        // 1. 과제 정보 가져오기
        Map<String, Object> result = taskService.getTaskDetailByTaskNo(taskNo);

        // 2. 업로드된 이미지 및 파일 정보 가져오기
        List<UploadFile> files = taskService.findFilesById(taskNo);
        List<UploadFile> images = taskService.findImagesById(taskNo);
        
        // 3. 모델에 입력
        model.addAttribute("images", images);
        model.addAttribute("files", files);
        model.addAttribute("taskInfo", result);

        return "lms/task/detail";
    }
    /**
     * 과제 공지사항 삭제
     */
    @PostMapping("/delete")
    @ResponseBody
    public String deleteTask(@RequestParam int taskNo) {
        taskService.deleteTask(taskNo);
        return null;
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
    @GetMapping("/attach/{taskNo}/{index}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable int taskNo,
                                                   @PathVariable int index) throws MalformedURLException {
        List<UploadFile> files = taskService.findFilesById(taskNo); 

        String uploadFileName = files.get(index).getUploadFileName();
        String storeFileName = files.get(index).getStoreFileName();

        UrlResource resource = new UrlResource("file:" + fileStoreService.getFullPath(storeFileName));

        log.info("uploadFileName={}", uploadFileName);
        log.info("storeFileName={}", storeFileName);
        
        // 한글 깨짐 방지를 위한 인코딩
        String encodedUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);

        // 파일 다운로드를 위한 규약. 사용하지 않을 경우 브라우저에서 다운이 아닌 읽기가 동작
        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }
}
