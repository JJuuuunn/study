package com.ssg.webmvc_member.controller;

import com.ssg.webmvc_member.dto.MemberDTO;
import com.ssg.webmvc_member.dto.ModifiedDTO;
import com.ssg.webmvc_member.service.MemberService;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@WebServlet(name = "MemberController", urlPatterns = "/member/*")
public class MemberController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        log.info("pathInfo: " + pathInfo);
        System.out.println("pathInfo: " + pathInfo);
        MemberService memberService = MemberService.INSTANCE;

        switch (pathInfo) {
            case "/addMember.do" -> req.getRequestDispatcher("/WEB-INF/member/addMember.jsp").forward(req, resp);
            case "/modMember.do" -> {
                String id = req.getParameter("id");
                MemberDTO member = memberService.getMember(id);
                if (member == null) {
                    resp.sendRedirect("/member/listMembers.do");
                } else {
                    req.setAttribute("member", member);
                    req.getRequestDispatcher("/WEB-INF/member/modMember.jsp").forward(req, resp);
                }
            }
            case "/delMember.do" -> {
                String id = req.getParameter("id");
                memberService.withDraw(id);
                resp.sendRedirect("/member/listMembers.do");
            }
            case "/listMembers.do", "/*" -> {
                List<MemberDTO> memberList = MemberService.INSTANCE.listAll();

                req.setAttribute("memberList", memberList);
                req.getRequestDispatcher("/WEB-INF/member/listMember.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemberService memberService = MemberService.INSTANCE;
        String pathInfo = req.getPathInfo();

        switch (pathInfo) {
            case "/addMember.do" -> {
                MemberDTO dto = MemberDTO.builder()
                        .id(req.getParameter("id"))
                        .name(req.getParameter("name"))
                        .email(req.getParameter("email"))
                        .password(req.getParameter("password"))
                        .build();

                memberService.singUp(dto);
                resp.sendRedirect("/member/listMembers.do");
            }
            case "/modMember.do" -> {
                ModifiedDTO dto = ModifiedDTO.builder()
                        .id(req.getParameter("id"))
                        .name(req.getParameter("name"))
                        .email(req.getParameter("email"))
                        .password(req.getParameter("password"))
                        .build();

                memberService.modify(dto);
                resp.sendRedirect("/member/listMembers.do");
            }
        }
    }
}
