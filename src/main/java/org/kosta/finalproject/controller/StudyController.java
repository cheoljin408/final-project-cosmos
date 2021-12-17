package org.kosta.finalproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.kosta.finalproject.config.auth.LoginUser;
import org.kosta.finalproject.config.auth.dto.SessionMember;
import org.kosta.finalproject.model.domain.CategoryLangDTO;
import org.kosta.finalproject.model.domain.CategoryTypeDTO;
import org.kosta.finalproject.model.domain.StudyDTO;
import org.kosta.finalproject.model.domain.StudyMemberDTO;
import org.kosta.finalproject.service.StudyService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
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
    public String studylistmain(@LoginUser SessionMember member, Model model){
        if(member != null) {
            model.addAttribute("member", member);
            model.addAttribute("picture", member.getPicture());
        }
        List<StudyMemberDTO> result = studyService.getAllList();

        model.addAttribute("studyList", result);
        return "studylist/study-list-main";
    }


    @GetMapping("/getStudyListByStudyNameAndDesc")
    @ResponseBody
    public List<Map<String, Object>> getStudyListByStudyNameAndDesc(@LoginUser SessionMember member, Model model, @RequestParam String searchWord){
        if(member != null) {
            model.addAttribute("member", member);
            model.addAttribute("picture", member.getPicture());
        }
        List<Map<String, Object>> result = studyService.getStudyListByStudyNameAndDesc(searchWord);
        System.out.println(result);
        return result;
    }
    /**
     *   카테고리 버튼 ->  Ajax를 통해서 -> 비동기 처리로 리스트를 화면에 뿌려줌
     *   @return :
     */
    @GetMapping("/getStudyListByCategory")
    @ResponseBody
    public List<Map<String, Object>> getStudyListByCategory(@LoginUser SessionMember member, Model model, @RequestParam String categoryVal){
        if(member != null) {
            model.addAttribute("member", member);
            model.addAttribute("picture", member.getPicture());
        }
        System.out.println("StudyController.getStudyListByCategory");
        System.out.println("categoryVal = " + categoryVal);
        List<Map<String, Object>> result = studyService.getStudyListByCategory(categoryVal);
        System.out.println("result = " + result.toString());
        return result;
    }

    /**
     * 스터디 등록 폼
     */
    @GetMapping("registerStudy")
    public String registerStudy() {
        return "study/register-study";
    }

    /**
     * 스터디 등록
     * 1. 사용자가 입력한 데이터를 StudyDTO로 set 한다
     * 2. Service쪽 registerStudy()를 호출해 스터디 및 스터디리더 역할 등록
     * @param member
     * @param jsonData
     */
    @Transactional
    @ResponseBody
    @PostMapping("/registerStudy")
    public int registerStudy(@LoginUser SessionMember member, @RequestBody HashMap<String, String> jsonData, Model model) {
        if(member != null) {
            model.addAttribute("member", member);
            model.addAttribute("picture", member.getPicture());
        }
        // log.info("jsonData = {}", jsonData);
        // log.info("studyName = {}", jsonData.get("studyName"));
        // log.info("categoryTypeNo = {}", jsonData.get("categoryTypeNo"))
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

        studyService.registerStudy(studyDTO);
        // log.info("email: {}", member.getEmail());
        studyService.registerStudyMemberRole(member.getEmail());
        // model.addAttribute("studyNo", studyDTO.getStudyNo());
        return studyDTO.getStudyNo();
    }

    /**
     * @param member
     * @param model
     * @param studyNo
     * studyNo를 제공받아 해당 스터디의 상세 정보를 조회한다
     * 1. 해당 스터디의 스터디 리더는 수정, 삭제 버튼이 보인다
     * 2. 스터디에 속하지 않은 사용자는 신청 버튼이 보인다
     * 3. 스터디원은 어떠한 버튼도 보이지 않는다
     */
    @RequestMapping("/studyDetail")
    public String studyDetail(@LoginUser SessionMember member, Model model, int studyNo) {
        if(member != null) {
            model.addAttribute("member", member);
            model.addAttribute("picture", member.getPicture());
        }
        model.addAttribute("study", studyService.getStudyDetailByStudyNo(studyNo));
        // log.info("studyInfo:{}", studyService.getStudyDetailByStudyNo(studyNo));
        // 스터디원 or 스터디리더 or 둘 다 아닌지 판단
        String role = studyService.findStudyMemberRoleByStudyNo(studyNo, member.getEmail());
        if (role == null) {
            role = "일반회원";
        }
        model.addAttribute("role", role);
        return "study/study-detail";
    }
}
