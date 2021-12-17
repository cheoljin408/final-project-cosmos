package org.kosta.finalproject;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kosta.finalproject.model.domain.*;
import org.kosta.finalproject.model.mapper.MemberMapper;
import org.kosta.finalproject.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        // 스터디 리스트 번호 조회
        List<StudyMemberDTO> list = studyService.getStudyList();
        for (StudyMemberDTO studyMemberDTO : list) {
            System.out.println("study no = " + studyMemberDTO.getStudyDTO().getStudyNo());
            System.out.println("email = " + studyMemberDTO.getMemberDTO().getEmail());
            System.out.println("study role = " + studyMemberDTO.getStudyMemberRole());
        }
        // test1. 등록 확인
        // assertThat(studyService.getStudyDetailByStudyNo(35)).isNotNull();

        // test2. 스터디 상태가 모집중인가
        // assertThat(studyDTO2.getStudyStateDTO().getStudyState()).isEqualTo("모집중");

    }

    @Test
    @DisplayName("카테고리 유형, 언어 조회")
    void findCategoryTest(){
        //given

        //when
        List<CategoryTypeDTO> ctl = studyService.getCategoryTypeList();
        List<CategoryLangDTO> cll = studyService.getCategoryLangList();

        //then
        assertThat(ctl).isNotNull();
        assertThat(cll).isNotNull();
        System.out.println("cll = " + cll);
        System.out.println("ctl = " + ctl);
        /*for (CategoryTypeDTO ct : ctl) {
            System.out.println("ct No = " + ct.getCategoryTypeNo());
            System.out.println("ct Name = " + ct.getCategoryType());
        }
        for (CategoryLangDTO cl : cll) {
            System.out.println("cl No = " + cl.getCategoryLangNo());
            System.out.println("cl Name = " + cl.getCategoryLang());
        }*/
    }

    @Test
    @DisplayName("스터디 그룹 상세보기")
    void 스터디그룹상세보기(){
        //given

        //when

        //then

    }

    @Test
    @DisplayName("스터디 모집 정보 수정")
    void 스터디모집정보수정(){
        //given

        //when

        //then

    }
}
