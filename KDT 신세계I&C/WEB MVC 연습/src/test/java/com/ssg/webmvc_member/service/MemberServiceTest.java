package com.ssg.webmvc_member.service;

import com.ssg.webmvc_member.dao.MemberDao;
import com.ssg.webmvc_member.dto.MemberDTO;
import com.ssg.webmvc_member.dto.ModifiedDTO;
import com.ssg.webmvc_member.dto.SignInDTO;
import com.ssg.webmvc_member.vo.MemberVO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("MemberService 테스트")
public class MemberServiceTest {
    private MemberService memberService;
    private MemberDao memberDao;

    @BeforeEach
    public void setUp() {
        memberService = MemberService.INSTANCE;
        memberDao = new MemberDao();

        MemberVO memberVO = getMemberVO();
        memberDao.save(memberVO);
    }

    private static MemberVO getMemberVO() {
        return MemberVO.builder()
                .id("testId1")
                .password("testPw1")
                .name("testName1")
                .email("testEmail1@test.com")
                .build();
    }

    @AfterEach
    public void tearDown() {
        memberDao.deleteTestData();
    }

    @Test
    @DisplayName("MemberService.singUp - 회원가입 성공")
    public void givenMemberDTO_whenSignUp_thenSuccess() {
        // given
        MemberDTO memberDTO = MemberDTO.builder()
                .id("testId2")
                .password("testPw2")
                .name("testName2")
                .email("testEmail2@test.com")
                .build();

        // when
        memberService.singUp(memberDTO);

        // then
        MemberDTO findDTO = memberDao.findById(memberDTO.id()).get();
        assertThat(findDTO.id()).isEqualTo(memberDTO.id());
        assertThat(findDTO.password()).isEqualTo(memberDTO.password());
        assertThat(findDTO.name()).isEqualTo(memberDTO.name());
        assertThat(findDTO.email()).isEqualTo(memberDTO.email());
    }

    @Test
    @DisplayName("MemberService.singIn - 로그인 성공")
    public void givenSignInDTO_whenSignIn_thenSuccess() {
        // given

        SignInDTO signInDTO = SignInDTO.builder()
                .id("testId1")
                .password("testPw1")
                .build();

        // when
        MemberDTO memberDTO = memberService.signIn(signInDTO);

        // then
        assertThat(memberDTO.id()).isEqualTo("testId1");
        assertThat(memberDTO.password()).isEqualTo("testPw1");
        assertThat(memberDTO.name()).isEqualTo("testName1");
        assertThat(memberDTO.email()).isEqualTo("testEmail1@test.com");
    }

    @Test
    @DisplayName("MemberService.modify - 회원정보 수정 성공")
    public void givenModifiedDTO_whenModify_thenSuccess() {
        // given

        ModifiedDTO modifiedDTO = ModifiedDTO.builder()
                .id("testId1")
                .password("testPw1")
                .name("modifiedName")
                .email("testEmail1@test.com")
                .build();

        // when
        memberService.modify(modifiedDTO);

        // then
        MemberDTO findDTO = memberDao.findById(modifiedDTO.id()).get();
        assertThat(findDTO.name()).isEqualTo(modifiedDTO.name());
    }

    @Test
    @DisplayName("MemberService.withDraw - 회원탈퇴 성공")
    public void givenId_whenWithDraw_thenSuccess() {
        // given
        String id = "testId1";

        // when
        memberService.withDraw(id);

        // then
        assertThat(memberDao.existsById(id)).isFalse();
    }

    @Test
    @DisplayName("MemberService.listAll - 전체 회원 조회 성공")
    public void givenNothing_whenListAll_thenSuccess() {
        // given
        MemberVO memberVO = getMemberVO();
        memberDao.save(memberVO);

        // when
        memberService.listAll();

        // then
        assertThat(memberDao.findAll().get()).isNotEmpty();
    }

    @Test
    @DisplayName("MemberService.getMember - 회원 조회 성공")
    public void givenId_whenGetMember_thenSuccess() {
        // given
        String id = "testId1";

        // when
        memberService.getMember(id);

        // then
        MemberDTO memberDTO = memberDao.findById(id).get();
        assertThat(memberDTO.id()).isEqualTo(id);
        assertThat(memberDTO.password()).isEqualTo("testPw1");
        assertThat(memberDTO.name()).isEqualTo("testName1");
        assertThat(memberDTO.email()).isEqualTo("testEmail1@test.com");
    }

    @Test
    @DisplayName("MemberService.checkMember - 회원 존재 여부 확인 성공")
    public void givenMemberDTO_whenCheckMember_thenSuccess() {
        // given
        MemberDTO memberDTO = MemberDTO.from(getMemberVO());

        // when
        memberService.checkMember(memberDTO);

        // then
        assertThat(memberDao.existsById(memberDTO.id())).isTrue();
    }
}