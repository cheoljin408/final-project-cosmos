package org.kosta.finalproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.kosta.finalproject.config.auth.LoginUser;
import org.kosta.finalproject.config.auth.dto.SessionMember;
import org.kosta.finalproject.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class ApplyController {

    private final ApplyService applyService;

    @Autowired
    public ApplyController(ApplyService applyService) {
        this.applyService = applyService;
    }

    /**
     * 내가 신청한 스터디 수락결과 알람 리스트
     * @param member
     * @param model
     * @return
     */
    @GetMapping("/alarm")
    public String getAlarmList(@LoginUser SessionMember member, Model model) {
        if(member != null) {
            model.addAttribute("member", member);
            model.addAttribute("picture", member.getPicture());
        }
        // allList : 참가신청한 스터디의 모든 리스트
        // alarmListOkAndNo: 참가신청한 스터디 중 참가신청 결과가 "수락완료" or "수락거부"인 참가신청 리스트
        // alarmListWait: 참가신청한 스터디 중 참가신청 결과가 "수락대기"인 참가신청 리스트
        List<Map<String, Object>> allList = applyService.getAlarmList(member.getEmail());
        List<Map<String, Object>> alarmListOkAndNo = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> alarmListWait = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < allList.size(); i++) {
            //내가 참가신청한 스터디 중에서 결과가 수락 or 거절인 리스트 추가
            if(!allList.get(i).get("AST_APPLY_STATE_CODE").equals("WAIT") && allList.get(i).get("EMAIL").equals(member.getEmail())) {
                alarmListOkAndNo.add(allList.get(i));
            }
            //대기중인 스터디 추가
            else if(allList.get(i).get("AST_APPLY_STATE_CODE").equals("WAIT") && allList.get(i).get("EMAIL").equals(member.getEmail())) {
                alarmListWait.add(allList.get(i));
            }
        }
        model.addAttribute("alarmListOkAndNo", alarmListOkAndNo);
        model.addAttribute("alarmListWait", alarmListWait);
        return "studyapplyalarm/apply-alarm";

    }
    /**
     *
     *  스터디 신청서에서 팀장이 수락 버튼을 누른경우 해당 로직을 실행하는 함수 ( Ajax 통신 )
     *
     * @return
     */
    @PostMapping("/apply/refuse")
    @ResponseBody
    public int applyRefuse(@RequestParam String applyNo){
        applyService.applyRefuse(Integer.parseInt(applyNo));
        return 0;
    }
    @PostMapping("/apply/accept")
    @ResponseBody
    public int applyAccept(@RequestBody Map<String, Object> param){
        log.debug("param[email] = {}, param[study_no] = {}, param[apply_no] = {}", param.get("email"), param.get("study_no"), param.get("apply_no"));
        applyService.applyAccept(param);
        return 1;
    }

    /**
     * 내가 만든 스터디에 참가신청한 회원들의 리스트 추출
     * @param member
     * @param model
     * @return
     */
    @GetMapping("/requestedApply")
    public String requestedApplyList(@LoginUser SessionMember member, Model model) {
        if(member != null) {
            model.addAttribute("member", member);
            model.addAttribute("picture", member.getPicture());
        }

        List<Map<String, Object>> requestedApplyList = applyService.requestedApplyList(member.getEmail());
        List<Map<String, Object>> isStudyLeader = applyService.isStudyLeader(member.getEmail());
        model.addAttribute("requestedApplyList", requestedApplyList);
        model.addAttribute("isStudyLeader", isStudyLeader);
        return "studyapplyalarm/apply-request-list";
    }

    @ResponseBody
    @PostMapping("applyStudy")
    public int applyStudy(@LoginUser SessionMember member, @RequestBody HashMap<String, String> jsonData, Model model){
        if(member != null) {
            model.addAttribute("member", member);
            model.addAttribute("picture", member.getPicture());
        }
        return applyService.registerApplyStudy(jsonData); //성공했으면 return 1, 이미 등록되어있어서 등록이 안됏다면 return 0
    }
}
