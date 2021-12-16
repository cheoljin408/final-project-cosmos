package org.kosta.finalproject.controller;

import org.kosta.finalproject.model.domain.StudyMemberDTO;
import org.kosta.finalproject.service.StudyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/study")
public class StudyController {

    @Resource
    private StudyService studyService;

    /**
     *  등록된 스터디 리스트를 조회해서 -> model 에 넣고 -> 페이지에 뿌려줌
     *
     * @param model <- List<StudyMemberDTO>
     * @return : 스터디 리스트 조회 page
     */

    @GetMapping("/list")
    public String studylistmain(Model model){
        List<StudyMemberDTO> result = studyService.getAllList();

        model.addAttribute("studyList", result);
        return "studylist/study-list-main";
    }
}