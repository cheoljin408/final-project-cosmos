package org.kosta.finalproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.kosta.finalproject.config.auth.LoginUser;
import org.kosta.finalproject.config.auth.dto.SessionMember;
import org.kosta.finalproject.model.domain.*;
import org.kosta.finalproject.service.PagingService;
import org.kosta.finalproject.service.StudyCommentService;
import org.kosta.finalproject.service.StudyMemberService;
import org.kosta.finalproject.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/study")
public class StudyController {


    private final StudyService studyService;
    private final StudyCommentService studyCommentService;
    private final PagingService pagingService;
    private final StudyMemberService studyMemberService;

    @Autowired
    public StudyController(StudyService studyService, StudyCommentService studyCommentService, PagingService pagingService,StudyMemberService studyMemberService) {
        this.studyService = studyService;
        this.studyCommentService = studyCommentService;
        this.pagingService = pagingService;
        this.studyMemberService=studyMemberService;
    }

    /**
     *  등록된 스터디 리스트를 조회해서 -> model 에 넣고 -> 페이지에 뿌려줌
     *
     * @param model <- List<StudyMemberDTO>
     * @return : 스터디 리스트 조회 page
     */

    @GetMapping("/list")
    public String studylistmain(Model model, @RequestParam(required = false) Object pageNo){
        log.info("pageNo: {}", pageNo);

        // paging을 위한 스터디 리스트의 전체 수 조회
        int totalCount = pagingService.getTotalCountOfStudyList();
        log.info("totalCount: {}", totalCount);

        PagingBean pagingBean = null;

        // pageNo Null Check
        if(pageNo == null) {
            pagingBean = new PagingBean(totalCount);
        } else {
            pagingBean = new PagingBean(totalCount,  Integer.valueOf((String)pageNo));
            log.info("totalCount: {}", totalCount);
            log.info("Integer.valueOf((String)pageNo): {}", Integer.valueOf((String)pageNo));
        }

        // pagingBean을 model에 할당
        model.addAttribute("pagingBean", pagingBean);

        // studyList를 model에 할당
        // List<StudyMemberDTO> result = studyService.getAllList();
        // model.addAttribute("studyList", result);
        List<StudyMemberDTO> studyList = pagingService.getStudyListByPageNo(pagingBean.getStartRowNumber(), pagingBean.getEndRowNumber());
        model.addAttribute("studyList", studyList);

        return "studylist/study-list-main";
    }

    /**
     * 스터디 등록 폼
     */
    @GetMapping("/registerStudy")
    public String registerStudy() {
        return "study/register-study";
    }

    /**
     * 스터디 등록
     * 사용자가 입력한 데이터로 스터디를 등록한 뒤 작성자는 스터디리더로 역할 등록
     */
    @Transactional
    @ResponseBody
    @PostMapping("/registerStudy")
    public int registerStudy(@LoginUser SessionMember member, @RequestBody HashMap<String, String> jsonData) {
        // log.info("jsonData = {}", jsonData);
        studyService.registerStudy(jsonData);
        studyService.registerStudyMemberRole(member.getEmail());
        return 0;
    }

    /**
     * studyNo를 제공받아 해당 스터디의 상세 정보를 조회한다
     * 1. 해당 스터디의 스터디 리더는 수정, 삭제 버튼이 보인다
     * 2. 스터디에 속하지 않은 사용자는 신청 버튼이 보인다
     * 3. 스터디원은 어떠한 버튼도 보이지 않는다
     * @return: 스터디 상세보기 페이지
     */
    @Transactional
    @GetMapping("/studyDetail/{studyNo}")
    public String studyDetail(@PathVariable int studyNo, @LoginUser SessionMember member, Model model) {
        if(member != null) {
            model.addAttribute("member", member);
        }
        model.addAttribute("study", studyService.getStudyDetailByStudyNo(studyNo));
        // log.info("studyInfo:{}", studyService.getStudyDetailByStudyNo(studyNo));
        // 스터디원 or 스터디리더 or 둘 다 아닌지 판단
        String role = studyService.findStudyMemberRoleByStudyNo(studyNo, member.getEmail());
        if (role == null) {
            role = "일반회원";
        }
        model.addAttribute("role", role);

        // 댓글 데이터 불러오기
        List<StudyCommentDTO> allStudyCommentList = studyCommentService.getAllStudyCommentList(studyNo);
        System.out.println("allStudyCommentList = " + allStudyCommentList);
        model.addAttribute("studyCommentList", allStudyCommentList);

        return "study/study-detail";
    }

    /**
     * 스터디 정보 수정
     * @return: 스터디 수정 폼 페이지
     */
    @GetMapping("/modifyStudy/{studyNo}")
    public String modifyStudy(@PathVariable int studyNo, Model model, @LoginUser SessionMember member) {
        // log.info("studyNo:{}",studyNo);
        if (studyService.findStudyMemberRoleByStudyNo(studyNo, member.getEmail()) == null) {
            log.info("잘못된 접근 방식: 스터디 수정 페이지는 스터디를 등록한 리더만 요청 가능");
            return "redirect:/";
        }
        model.addAttribute("study", studyService.getStudyDetailByStudyNo(studyNo));
        model.addAttribute(studyNo);
        return "study/modify-study";
    }

    /**
     * 스터디 정보 수정 성공 후 메인 페이지로 이동
     */
    @ResponseBody
    @PutMapping("/modifyStudy/{studyNo}")
    public int modifyStudy(@PathVariable int studyNo, Model model, @RequestBody Map<String, String> jsonData) {
        jsonData.put("studyNo", String.valueOf(studyNo));
        log.info("jsonData:{}", jsonData);
        studyService.modifyStudy(jsonData);
        model.addAttribute("studyNo", studyNo);
        return 0;
    }

    /**
     * 나의 스터디 리스트 가져오기
     */
    @GetMapping("/mystudy")
    public String mystudy(@LoginUser SessionMember member,Model model){
        System.out.println("member.getEmail() = " + member.getEmail());

        model.addAttribute("studyList",studyService.getMystudyListByEmail(member.getEmail()));

        return "studylist/mystudy-list";
    }

    /**
     * 스터디 상태 변경
     */
    @GetMapping("/updateState/{studyNo}/{studyState}")
    public String updateState(@PathVariable int studyNo, @PathVariable String studyState,@LoginUser SessionMember member,Model model){
        log.info("findStudyMemberRoleByStudyNo : {}",studyService.findStudyMemberRoleByStudyNo(studyNo, member.getEmail()));
        if(studyService.findStudyMemberRoleByStudyNo(studyNo, member.getEmail()).equals("스터디리더")){
            studyService.updateState(studyNo,studyState);
        }
        return "forward:/lms/"+studyNo;
    }

}
