package ua.vlasovEugene.servletBankSystem.filter;

import ua.vlasovEugene.servletBankSystem.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {
    private final String INDEX_PAGE = "/WEB-INF/view/index.jsp";

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        User userFromSession = (User) session.getAttribute("user");
        if(userFromSession!=null){
            filterChain.doFilter(servletRequest,servletResponse);
        } else {
            String login = request.getParameter("email");
            String password = request.getParameter("password");

            if(login!=null&&password!=null){
                filterChain.doFilter(servletRequest,servletResponse);
            } else {
                response.sendRedirect("/");
            }
        }
    }
}