package org.kosta.finalproject.controller;

import org.kosta.finalproject.service.ApplyAlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplyAlarmController {

    private final ApplyAlarmService applyAlarmService;

    @Autowired
    public ApplyAlarmController(ApplyAlarmService applyAlarmService) {
        this.applyAlarmService = applyAlarmService;
    }

    @GetMapping("/alarm")
    public String alarm() {
        return "apply-alarm";
    }

}
