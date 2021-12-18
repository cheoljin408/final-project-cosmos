package org.kosta.finalproject.controller;

import org.kosta.finalproject.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ApplyController {

    private final ApplyService applyService;

    @Autowired
    public ApplyController(ApplyService applyService) {
        this.applyService = applyService;
    }

    @GetMapping("/alarm")
    public String getAlarmList(Model model) {
        System.out.println(applyService.alarm());
        return "apply-alarm";
    }

    /**
     *
     *  스터디 신청서에서 팀장이 수락 버튼을 누른경우 해당 로직을 실행하는 함수 ( Ajax 통신 )
     *
     * @return
     */
    @GetMapping("/apply")
    @ResponseBody
    public String applyRefuse(@RequestParam int applyNo){
        return null;
    }
}
