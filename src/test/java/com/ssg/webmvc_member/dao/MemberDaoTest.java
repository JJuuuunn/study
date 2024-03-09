package com.ssg.webmvc_member.dao;

import com.ssg.webmvc_member.dto.MemberDTO;
import com.ssg.webmvc_member.dto.ModifiedDTO;
import com.ssg.webmvc_member.dto.SignInDTO;
import com.ssg.webmvc_member.vo.MemberVO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("MemberDao 테스트")
class MemberDaoTest {
    MemberDao memberDao;

    @BeforeEach
    void setUp() {
        memberDao = new MemberDao();
        memberDao.save(MemberVO.builder()
                .id("testId1")
                .password("testPw1")
                .name("testName1")
                .email("testEmail1")
                .build());
    }

    @AfterEach
    void tearDown() {
        memberDao.deleteTestData();
    }

    @Test
    @DisplayName("MemberDao.save - 회원가입 성공")
    public void givenMemberVO_whenSave_thenMemberVOIsSaved() {
        // given
        MemberVO memberVO = MemberVO.builder()
                .id("testId2")
                .password("testPw2")
                .name("testName2")
                .email("testEmail2")
                .build();

        // when
        int result = memberDao.save(memberVO);

        // then
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("MemberDao.existsById - ID가 존재하는 경우")
    public void givenId_whenExistsById_thenTrue() {
        // given
        String id = "testId1";

        // when
        boolean result = memberDao.existsById(id);

        // then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("MemberDao.existsById - ID가 존재하지 않는 경우")
    public void givenId_whenExistsById_thenFalse() {
        // given
        String id = "testId99999";

        // when
        boolean result = memberDao.existsById(id);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("MemberDao - 로그인 성공")
    public void givenSignInDTO_whenFindByIdAndPassword_thenMemberVOIsFound() {
        // given
        SignInDTO dto = SignInDTO.builder()
                .id("testId1")
                .password("testPw1")
                .build();

        // when
        Optional<MemberDTO> optional = memberDao.findByIdAndPassword(dto);
        MemberDTO memberDTO = optional.get();

        // then
        assertThat(memberDTO).isNotNull();
        assertThat(memberDTO.id()).isEqualTo("testId1");
        assertThat(memberDTO.password()).isEqualTo("testPw1");
    }

    @Test
    @DisplayName("MemberDao.update - 회원정보 수정 성공")
    public void givenModifiedDTO_whenUpdate_thenMemberVOIsUpdated() {
        // given
        ModifiedDTO dto = ModifiedDTO.builder()
                .id("testId1")
                .password("testPw1Modified")
                .name("testName1Modified")
                .email("testEmail1Modified")
                .build();

        // when
        int result = memberDao.update(dto);

        // then
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("MemberDao.deleteById - 회원정보 삭제 성공")
    public void givenId_whenDelete_thenMemberVOIsDeleted() {
        // given
        String id = "testId1";

        // when
        int result = memberDao.deleteById(id);

        // then
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("MemberDao.findAll - 회원 전체 조회 성공")
    public void given_whenFindAll_thenMemberListIsFound() {
        // given
        MemberVO memberVO1 = MemberVO.builder()
                .id("testId2")
                .password("testPw2")
                .name("testName2")
                .email("testEmail2")
                .build();
        MemberVO memberVO2 = MemberVO.builder()
                .id("testId3")
                .password("testPw3")
                .name("testName3")
                .email("testEmail3")
                .build();
        memberDao.save(memberVO1);
        memberDao.save(memberVO2);

        // when
        Optional<List<MemberDTO>> optional = memberDao.findAll();
        List<MemberDTO> memberList = optional.get();

        // then
        assertThat(memberList).extracting(MemberDTO::id).contains("testId1", "testId2");
        assertThat(memberList).extracting(MemberDTO::password).contains("testPw1", "testPw2");
        assertThat(memberList).extracting(MemberDTO::name).contains("testName1", "testName2");
        assertThat(memberList).extracting(MemberDTO::email).contains("testEmail1", "testEmail2");
    }
}