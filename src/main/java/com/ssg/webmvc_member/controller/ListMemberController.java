package com.ssg.webmvc_member.controller;

import com.ssg.webmvc_member.dto.MemberDTO;
import com.ssg.webmvc_member.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "listMemberController", value = "/member/listMembers.do")
public class ListMemberController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MemberDTO> memberList = MemberService.INSTANCE.listAll();

        req.setAttribute("memberList", memberList);
        req.getRequestDispatcher("/WEB-INF/member/listMember.jsp").forward(req, resp);
    }
}
