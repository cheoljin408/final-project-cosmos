package org.kosta.finalproject.controller;

import org.kosta.finalproject.config.auth.LoginUser;
import org.kosta.finalproject.config.auth.dto.SessionMember;
import org.kosta.finalproject.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ApplyController {

    private final ApplyService applyService;

    @Autowired
    public ApplyController(ApplyService applyService) {
        this.applyService = applyService;
    }

    /**
     * 스터디 참가신청 결과 알람 리스트
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
        List<Map<String, Object>> allList = applyService.getAlarmList();
        List<Map<String, Object>> alarmListOkAndNo = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> alarmListWait = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < allList.size(); i++) {
            if(!allList.get(i).get("AST_APPLY_STATE_CODE").equals("WAIT")) {
                alarmListOkAndNo.add(allList.get(i));
            }
            //대기중인 스터디 추가
            else {
                alarmListWait.add(allList.get(i));
            }
        }
        model.addAttribute("alarmListOkAndNo", alarmListOkAndNo);
        model.addAttribute("alarmListWait", alarmListWait);
        System.out.println(alarmListWait);
        return "apply-alarm";
    }
    /**
     *
     *  스터디 신청서에서 팀장이 수락 버튼을 누른경우 해당 로직을 실행하는 함수 ( Ajax 통신 )
     *
     * @return
     */
    @GetMapping("/apply/refuse")
    @ResponseBody
    public String applyRefuse(@RequestParam int applyNo){
        applyService.applyRefuse(applyNo);
        return null;
    }
    @GetMapping("/apply/accept")
    @ResponseBody
    @Transactional
    public String applyAccept(@RequestParam String email,
                              @RequestParam int applyNo,
                              @RequestParam int studyNo){
        applyService.applyAccept(email, applyNo, studyNo);
        return null;
    }
}
