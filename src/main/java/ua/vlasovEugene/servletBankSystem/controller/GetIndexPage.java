package ua.vlasovEugene.servletBankSystem.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class GetIndexPage implements Command {
    private final String INDEX = "/WEB-INF/view/index.jsp";
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(INDEX).forward(request,response);
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (this == o) result =  true;
        if (o == null || getClass() != o.getClass()) return false;
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(INDEX);
    }
}
