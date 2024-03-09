package com.ssg.webmvc_member.controller;

import com.ssg.webmvc_member.dto.MemberDTO;
import com.ssg.webmvc_member.dto.ModifiedDTO;
import com.ssg.webmvc_member.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ModMemberController", urlPatterns = "/member/modMember.do")
public class ModMemberController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        MemberDTO member = MemberService.INSTANCE.getMember(id);

        if (member == null) {
            resp.sendRedirect("/member/listMembers.do");
        } else {
            req.setAttribute("member", member);
            req.getRequestDispatcher("/WEB-INF/member/modMember.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ModifiedDTO dto = ModifiedDTO.builder()
                .id(req.getParameter("id"))
                .name(req.getParameter("name"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .build();

        MemberService.INSTANCE.modify(dto);

        resp.sendRedirect("/member/listMembers.do");
    }
}
