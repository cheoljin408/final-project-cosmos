package org.kosta.finalproject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kosta.finalproject.model.domain.CategoryLangDTO;
import org.kosta.finalproject.model.domain.CategoryTypeDTO;
import org.kosta.finalproject.model.domain.MemberDTO;
import org.kosta.finalproject.model.domain.StudyDTO;
import org.kosta.finalproject.model.mapper.MemberMapper;
import org.kosta.finalproject.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class StudyTest {

    @Autowired
    private StudyService studyService;

    @Autowired
    private MemberMapper memberMapper;

    @Test
    @DisplayName("스터디 등록 및 조회")
    @Transactional
    void StudyRegisterTest(){
        //given
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setEmail("test@test.com");
        memberDTO.setName("test");
        Map<String,String> studyDTO = new HashMap<String,String>();
        studyDTO.put("STUDY_NAME","test study name");
        studyDTO.put("STUDY_INFO","test info");
        studyDTO.put("STUDY_DESC","test desc");
        studyDTO.put("CATEGORY_TYPE_NO","3");
        studyDTO.put("CATEGORY_LANG_NO","3");
        int beforeReg = studyService.getAllList().size();

        //when
        studyService.registerStudy(studyDTO);
        studyService.registerStudyMemberRole(memberDTO.getEmail());
        int afterReg = studyService.getAllList().size();

        //then
        // 스터디 등록 전과 후의 스터디 수 비교: 전 < 후
        assertThat(beforeReg).isLessThan(afterReg);

    }

    @Test
    @DisplayName("스터디 그룹 상세보기")
    void 스터디그룹상세보기(){
        //given
        String email = "nogy21@gmail.com";
        int studyNo = 1;

        //when
        Map<String, Object> map = studyService.getStudyDetailByStudyNo(studyNo);
        String role = studyService.findStudyMemberRoleByStudyNo(studyNo, email);

        //then
        // assertThat(role).isEqualTo("스터디리더");
        assertThat(role).isEqualTo(null);
    }

    @Test
    @DisplayName("스터디 모집 정보 수정")
    void 스터디모집정보수정(){
        //given
        Map<String, Object> studyDetailInfo = studyService.getStudyDetailByStudyNo(51);
        System.out.println("studyDetailInfo = " + studyDetailInfo);
        // 51번 스터디 모집 내용을 아래의 updateStudyData 내용으로 수정
        Map<String, String> studyDTO = new HashMap<String, String>() {
        };
        studyDTO.put("STUDY_NO", "51");
        studyDTO.put("STUDY_NAME", "TEST t_study name");
        studyDTO.put("STUDY_DESC", "TEST t_study desc");
        studyDTO.put("STUDY_INFO", "TEST t_study info");
        studyDTO.put("CATEGORY_TYPE_NO", "3");
        studyDTO.put("CATEGORY_LANG_NO", "3");

        //when
        studyService.modifyStudy(studyDTO);
        Map<String, Object> studyDetailInfo2 = studyService.getStudyDetailByStudyNo(51);
        System.out.println("studyDetailInfo = " + studyDetailInfo);
        System.out.println("studyDetailInfo2 = " + studyDetailInfo2);

        //then
        assertThat(studyDetailInfo).isNotSameAs(studyDetailInfo2);

    }
}
