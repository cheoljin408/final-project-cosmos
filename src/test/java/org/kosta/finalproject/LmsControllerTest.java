package org.kosta.finalproject;

import org.junit.jupiter.api.Test;
import org.kosta.finalproject.model.domain.StudyMemberDTO;
import org.kosta.finalproject.service.StudyMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
public class LmsControllerTest {

    private final StudyMemberService studyMemberService;

    @Autowired
    public LmsControllerTest(StudyMemberService studyMemberService) {
        this.studyMemberService = studyMemberService;
    }

    @Test
    void getStudyNameListTest() {
        // given
        Map<String, Object> emailAndStudyNo = new HashMap<String, Object>();
        emailAndStudyNo.put("email", "cheoljin408@gmail.com");
        emailAndStudyNo.put("studyNo", 21);
        List<Map<String, Object>> studyNameList = studyMemberService.getStudyNameList(emailAndStudyNo);
        System.out.println("studyNameList = " + studyNameList);

        // when


        // then
        assertThat(studyNameList.size()).isEqualTo(7);
    }

    @Test
    void getAllStudyInfoTest() {
        // given
        String studyName = "qwer";
        int studyNo = 21;

        // when
        StudyMemberDTO allStudyInfo = studyMemberService.getAllStudyInfo(studyNo);
        System.out.println("allStudyInfo = " + allStudyInfo);

        //then
        assertThat(studyName).isEqualTo(allStudyInfo.getStudyDTO().getStudyName());
    }
}
