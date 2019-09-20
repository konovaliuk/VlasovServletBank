package ua.vlasovEugene.servletBankSystem.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class GetNewUserPage implements Command {
    private final String NEWUSER = "/WEB-INF/view/newuser.jsp";
    /**
     * The method takes the admin to the page for creating a new user
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(NEWUSER).forward(request,response);
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetNewUserPage that = (GetNewUserPage) o;
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(NEWUSER);
    }
}
