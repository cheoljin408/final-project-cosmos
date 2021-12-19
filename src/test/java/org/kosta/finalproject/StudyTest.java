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
        StudyDTO studyDTO = new StudyDTO();
        studyDTO.setStudyName("test study name");
        studyDTO.setStudyInfo("test info");
        studyDTO.setStudyDesc("test desc");

        CategoryLangDTO categoryLangDTO = new CategoryLangDTO();
        categoryLangDTO.setCategoryLangNo(1);
        studyDTO.setCategoryLangDTO(categoryLangDTO);

        CategoryTypeDTO categoryTypeDTO = new CategoryTypeDTO();
        categoryTypeDTO.setCategoryTypeNo(1);
        studyDTO.setCategoryTypeDTO(categoryTypeDTO);

        //when
        studyService.registerStudy(studyDTO);
        studyService.registerStudyMemberRole(memberDTO.getEmail());

        //then
        // 스터디 등록 시 스터디 번호가 생성되므로 이를 통해 등록된 스터디 내용을 조회할 수 있다
        Map<String, Object> studyDetailInfo = studyService.getStudyDetailByStudyNo(studyDTO.getStudyNo());
        // test1. 스터디 리더 등록 확인
        assertThat(studyDetailInfo.get("STUDY_MEMBER_ROLE")).isEqualTo("스터디리더");
        // test2. 스터디 모집 상태 확인
        assertThat(studyDetailInfo.get("STUDY_STATE")).isEqualTo("모집중");
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

        //when

        //then

    }
}
