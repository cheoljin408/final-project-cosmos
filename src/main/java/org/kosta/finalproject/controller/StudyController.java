package org.kosta.finalproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.kosta.finalproject.config.auth.LoginUser;
import org.kosta.finalproject.config.auth.dto.SessionMember;
import org.kosta.finalproject.model.domain.CategoryLangDTO;
import org.kosta.finalproject.model.domain.CategoryTypeDTO;
import org.kosta.finalproject.model.domain.StudyDTO;
import org.kosta.finalproject.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class StudyController {

    private StudyService studyService;

    @Autowired
    public StudyController(StudyService studyService) {
        this.studyService = studyService;
    }

    @GetMapping("registerStudy")
    public String registerStudy() {
        return "study/register-study";
    }

    @Transactional
    @ResponseBody
    @PostMapping("registerStudy")
    public Map<String, Object> registerStudy(@LoginUser SessionMember member, @RequestBody HashMap<String, String> jsonData, Model model) {
        /*log.info("jsonData = {}", jsonData);
        log.info("studyName = {}", jsonData.get("studyName"));
        log.info("categoryTypeNo = {}", jsonData.get("categoryTypeNo"));*/
        StudyDTO studyDTO = new StudyDTO();
        studyDTO.setStudyName(jsonData.get("studyName"));
        studyDTO.setStudyDesc(jsonData.get("studyDesc"));
        studyDTO.setStudyInfo(jsonData.get("studyInfo"));
        CategoryTypeDTO categoryTypeDTO = new CategoryTypeDTO();
        categoryTypeDTO.setCategoryTypeNo(Integer.parseInt(jsonData.get("categoryTypeNo")));
        CategoryLangDTO categoryLangDTO = new CategoryLangDTO();
        categoryLangDTO.setCategoryLangNo(Integer.parseInt(jsonData.get("categoryLangNo")));
        studyDTO.setCategoryTypeDTO(categoryTypeDTO);
        studyDTO.setCategoryLangDTO(categoryLangDTO);
        // log.info("delivered study data:{}", studyDTO);

        // 전달받은 스터디
        studyService.registerStudy(studyDTO);
        // log.info("email: {}", member.getEmail());
        studyService.registerStudyMemberRole(member.getEmail());
        // 등록된 스터디 조회한 뒤 스터디 상세 페이지로 이동
        Map<String, Object> studyDetailInfo = studyService.getStudyDetailByStudyNo(studyDTO.getStudyNo());
        // log.info("study detail info :{}", studyDetailInfo);
        return studyDetailInfo;
    }
}
