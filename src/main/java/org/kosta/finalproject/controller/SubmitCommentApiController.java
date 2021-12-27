package org.kosta.finalproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.kosta.finalproject.model.domain.StudyCommentDTO;
import org.kosta.finalproject.model.domain.SubmitCommentDTO;
import org.kosta.finalproject.model.domain.UploadFile;
import org.kosta.finalproject.config.auth.LoginUser;
import org.kosta.finalproject.config.auth.dto.SessionMember;
import org.kosta.finalproject.model.domain.SubmitCommentDTO;
import org.kosta.finalproject.model.domain.SubmitCommentFormDTO;
import org.kosta.finalproject.model.domain.UploadFile;
import org.kosta.finalproject.service.FileStoreService;
import org.kosta.finalproject.service.SubmitCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import org.kosta.finalproject.service.SubmitCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class SubmitCommentApiController {

    private final SubmitCommentService submitCommentService;

    @Autowired
    public SubmitCommentApiController(SubmitCommentService submitCommentService) {
        this.submitCommentService = submitCommentService;
    }
    
    // 과제 제출 등록
    @PostMapping("/api/registerTaskComment/{studyNo}/{taskNo}")
    public String registerTaskComment(@LoginUser SessionMember member, @PathVariable int studyNo, @PathVariable int taskNo, @ModelAttribute SubmitCommentFormDTO submitCommentFormDTO, RedirectAttributes redirectAttributes) {
        log.info("submitCommentFormDTO: {}", submitCommentFormDTO);
        try {
            // 파일 저장 및 DB insert 세팅
            UploadFile attachFile = fileStoreService.storeFile(submitCommentFormDTO.getAttachFile());

            SubmitCommentDTO submitCommentDTO = new SubmitCommentDTO();
            submitCommentDTO.setSubmitContent(submitCommentFormDTO.getSubmitContent());
            submitCommentDTO.setSubmitUploadFileName(attachFile.getUploadFileName());
            submitCommentDTO.setSubmitStoreFileName(attachFile.getStoreFileName());
            submitCommentDTO.setTaskNo(taskNo);
            submitCommentDTO.setEmail(member.getEmail());
            submitCommentDTO.setStudyNo(studyNo);

            // DB insert
            submitCommentService.registerSubmitComment(submitCommentDTO);

        } catch (IOException e) {
            e.printStackTrace();
        }

        redirectAttributes.addAttribute("studyNo", studyNo);
        redirectAttributes.addAttribute("taskNo", taskNo);

        return "redirect:/task/detail/{studyNo}/{taskNo}";
    }
    
    // 과제 제출 수정

    
    // 과제 제출 삭제
    @DeleteMapping("/api/deleteTaskComment/{studyNo}/{submitNo}")
    public String deleteTaskComment(@RequestBody int submitNo, @PathVariable int taskNo,
                                    @PathVariable int studyNo, Model model,
                                    RedirectAttributes redirectAttributes) {
        log.info("submitNo = {}", submitNo);
        System.out.println("submit = " + submitNo);

        // (submitNo) 해당 댓글을 삭제
        submitCommentService.deleteTaskComment(submitNo);

        // 2. 댓글리스트를 가져옴 -> redirect: 후에 가져오는게 더 적절함
//        List<SubmitCommentDTO> allTaskCommentList = submitCommentService.getAllTaskCommentList(taskNo);
//        model.addAttribute("taskCommentList", allTaskCommentList);

        // 3. redirect
        redirectAttributes.addAttribute("studyNo", studyNo);
        redirectAttributes.addAttribute("taskNo", taskNo);

        return "redirect:/detail/{studyNo}/{taskNo}";
    }
    
}