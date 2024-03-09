package com.ssg.webmvc_member.service;

import com.ssg.webmvc_member.dao.MemberDao;
import com.ssg.webmvc_member.dto.MemberDTO;
import com.ssg.webmvc_member.dto.ModifiedDTO;
import com.ssg.webmvc_member.dto.SignInDTO;
import com.ssg.webmvc_member.vo.MemberVO;

import java.util.List;
import java.util.Optional;

public enum MemberService {
    INSTANCE;

    MemberDao memberDao;

    MemberService() {
        memberDao = new MemberDao();
    }

    public void singUp(MemberDTO dto) {
        if (memberDao.existsById(dto.id())) {
            throw new RuntimeException("이미 존재하는 ID입니다.");
        } else {
            MemberVO vo = MemberVO.builder()
                    .id(dto.id())
                    .name(dto.name())
                    .email(dto.email())
                    .password(dto.password())
                    .build();
            memberDao.save(vo);
        }
    }

    public MemberDTO signIn(SignInDTO dto) {
        Optional<MemberDTO> optional = memberDao.findByIdAndPassword(dto);
        MemberDTO memberDTO = optional.orElseThrow(() -> new RuntimeException("로그인 실패"));
        return memberDTO;
    }

    public void modify(ModifiedDTO dto) {
        memberDao.update(dto);
    }

    public void withDraw(String id) {
        memberDao.deleteById(id);
    }

    public List<MemberDTO> listAll() {
        return memberDao.findAll().orElseThrow(() -> new RuntimeException("회원이 존재하지 않습니다."));
    }

    public MemberDTO getMember(String id) {
        return memberDao.findById(id).orElseThrow(() -> new RuntimeException("회원이 존재하지 않습니다."));
    }
}
