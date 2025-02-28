package com.ssg.webmvc_member.filter;

import com.ssg.webmvc_member.dto.MemberDTO;
import com.ssg.webmvc_member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.internal.util.Members;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = {"/member/*"})
public class SignInLoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        if(session.getAttribute("loginInfo") == null){
            System.out.println("로그인 정보가 없습니다.");
            response.sendRedirect("/signIn.do");
            return;
        } else {
            Object loginInfo = session.getAttribute("loginInfo");
            MemberDTO dto = (MemberDTO) loginInfo;
            if (MemberService.INSTANCE.checkMember(dto)) {
                log.info("로그인 정보가 있습니다.");
            } else {
                log.info("로그인 정보가 없습니다.");
                response.sendRedirect("/signIn.do");
                return;
            }
        }
        chain.doFilter(request, response);
    }
}
