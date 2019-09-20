package ua.vlasovEugene.servletBankSystem.filter;

import ua.vlasovEugene.servletBankSystem.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        User userFromSession = (User) session.getAttribute("user");

        if(userFromSession.getUserRole().equalsIgnoreCase("user")){
            filterChain.doFilter(servletRequest,servletResponse);
        } else {
            response.sendRedirect("/");
        }
    }
}
