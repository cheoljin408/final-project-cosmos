package org.kosta.finalproject.controller;

import org.kosta.finalproject.model.domain.StudyMemberDTO;
import org.kosta.finalproject.service.StudyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    /**
     *   카테고리 버튼 ->  Ajax를 통해서 -> 비동기 처리로 리스트를 화면에 뿌려줌
     *   @return :
     */
    @GetMapping("/getStudyListByCategory")
    @ResponseBody
    public List<Map<String, Object>> getStudyListByCategory(@RequestParam String categoryVal){
        System.out.println("StudyController.getStudyListByCategory");
        System.out.println("categoryVal = " + categoryVal);
        List<Map<String, Object>> result = studyService.getStudyListByCategory(categoryVal);
        System.out.println("result = " + result.toString());
        return result;
    }
}