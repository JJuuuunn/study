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
        boolean isExist = memberDao.existsById(dto.id());
        try {
            if (isExist) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MemberDTO signIn(SignInDTO dto) {
        MemberDTO memberDTO = null;
        try {
            memberDTO = memberDao.findByIdAndPassword(dto)
                    .orElseThrow(() -> new RuntimeException("로그인 실패"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return memberDTO;
    }

    public void modify(ModifiedDTO dto) {
        memberDao.update(dto);
    }

    public void withDraw(String id) {
        memberDao.deleteById(id);
    }

    public List<MemberDTO> listAll() {
        List<MemberDTO> memberList = null;
        try {
            memberList = memberDao.findAll().orElseThrow(() -> new RuntimeException("회원이 존재하지 않습니다."));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return memberList;
    }

    public MemberDTO getMember(String id) {
        MemberDTO dto = null;
        try {
            dto = memberDao.findById(id).orElseThrow(() -> new RuntimeException("해당 회원이 존재하지 않습니다."));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dto;
    }

    public boolean checkMember(MemberDTO dto) {
        boolean result = false;
        try {
            if (memberDao.existsByIdAndPassword(dto.id(), dto.password())) {
                result = true;
            } else {
                throw new RuntimeException("아이디 또는 비밀번호가 일치하지 않습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
