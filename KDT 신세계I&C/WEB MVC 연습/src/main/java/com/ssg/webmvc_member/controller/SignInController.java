package com.ssg.webmvc_member.controller;

import com.ssg.webmvc_member.dto.MemberDTO;
import com.ssg.webmvc_member.dto.SignInDTO;
import com.ssg.webmvc_member.service.MemberService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "SignInController", urlPatterns = "/signIn.do")
public class SignInController extends HttpServlet {
    MemberService memberService = MemberService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/member/signIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("SignInController doPost");
        SignInDTO dto = SignInDTO.builder()
                .id(req.getParameter("id"))
                .password(req.getParameter("password"))
                .build();
        MemberDTO member = memberService.signIn(dto);

        if (member != null) {
            HttpSession session = req.getSession();
            session.setAttribute("loginInfo", member);
            resp.sendRedirect("/member/listMembers.do");
        } else {
            resp.sendRedirect("/signIn.do?result=error");
        }
    }
}
