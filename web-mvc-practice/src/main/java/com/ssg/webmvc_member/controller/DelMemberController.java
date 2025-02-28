//package com.ssg.webmvc_member.controller;
//
//import com.ssg.webmvc_member.service.MemberService;
//
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.*;
//import java.io.IOException;
//
//@WebServlet(name = "DelMemberController", urlPatterns = "/member/delMember.do")
//public class DelMemberController extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String id = req.getParameter("id");
//        MemberService.INSTANCE.withDraw(id);
//
//        resp.sendRedirect("/member/listMembers.do");
//    }
//}
