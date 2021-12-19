package org.kosta.finalproject.apply;

import org.junit.jupiter.api.Test;
import org.kosta.finalproject.model.mapper.ApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplyStudyTest {

    @Autowired
    private ApplyMapper applyMapper;

    @Test
    void 스터디신청중복검사(){
        String studyNo = "8";
        String email="test@test.com";
        System.out.println(applyMapper.checkApplyByStudyNoAndEmail(studyNo,email));
    }

    @Test
    void 스터디신청(){
        String studyNo = "7";
        String applyContent = "테스트 신청서";
        String email="test@test.com";
        applyMapper.registerApplyStudy(studyNo,applyContent,email);
        System.out.println(applyMapper.checkApplyByStudyNoAndEmail("7","test@test.com"));
    }

    @Test
    void 스터디신청등록테스트(){
        String studyNo = "6";
        String applyContent = "테스트 신청서";
        String email="test@test.com";
        if(applyMapper.checkApplyByStudyNoAndEmail(studyNo,email)==0){
            applyMapper.registerApplyStudy(studyNo,applyContent,email);
        }
        System.out.println(applyMapper.checkApplyByStudyNoAndEmail(studyNo,email));
    }

}
