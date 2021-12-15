package org.kosta.finalproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudyController {

    @GetMapping("study-list-main")
    public String studylistmain(){
        return "study-list-main";
    }
}
