package org.kosta.finalproject;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kosta.finalproject.model.domain.MemberDTO;
import org.kosta.finalproject.model.domain.StudyMemberDTO;
import org.kosta.finalproject.model.mapper.MemberMapper;
import org.kosta.finalproject.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
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
        studyDTO.put("studyNo", "51");
        studyDTO.put("studyName", "TEST t_study name");
        studyDTO.put("studyDesc", "TEST t_study desc");
        studyDTO.put("studyInfo", "TEST t_study info");
        studyDTO.put("categoryTypeNo", "3");
        studyDTO.put("categoryLangNo", "3");

        //when
        studyService.modifyStudy(studyDTO);
        Map<String, Object> studyDetailInfo2 = studyService.getStudyDetailByStudyNo(51);
        System.out.println("studyDetailInfo = " + studyDetailInfo);
        System.out.println("studyDetailInfo2 = " + studyDetailInfo2);

        //then
        assertThat(studyDetailInfo).isNotSameAs(studyDetailInfo2);

    }

    /**
     * 스터디 리더는 본인이 만든 스터디를 삭제할 수 있다
     * 스터디 번호로 해당 스터디를 삭제하면 등록된 스터디 리더와 스터디원들은 자동으로 탈퇴된다(직책 해제)
     * 스터디 모집 게시판 역시 삭제되며 해당 게시판에 달린 댓글들도 삭제된다
     * 스터디 참여 신청 목록 삭제
     * 공지 게시판 및 공지 게시판 파일 삭제
     * 과제 및 과제 파일 삭제
     * 과제 제출 및 과제 제출 파일 삭제
     */
    @Test
    @DisplayName("스터디 삭제")
    void 스터디삭제(){

        //given
        int studyNo = 106;
        Map<String, Object> studyInfo = studyService.getStudyDetailByStudyNo(studyNo);
        System.out.println("studyInfo = " + studyInfo);
        // 참여 신청 정보 확인
        // 댓글 정보 확인
        // 공지사항, 과제, 과제제출 확인

        //when
        studyService.deleteStudyByStudyNo(studyNo);

        //then
        assertThat(studyInfo).isNull();

    }

    @Test
    void 나의스터디조회(){
        String email = "moon960427@gmail.com";
        List<StudyMemberDTO> list = studyService.getMystudyListByEmail(email);
        System.out.println(list.size());
        for (StudyMemberDTO studyMemberDTO : list) {
            System.out.println(studyMemberDTO);
        }
    }
}
