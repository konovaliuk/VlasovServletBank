package ua.vlasovEugene.servletBankSystem.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class LanguageChanger implements Command {
    private final String INDEX_PAGE = "/WEB-INF/view/index.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String language = request.getParameter("language");
        request.getSession().setAttribute("currentLang", language);

        request.getRequestDispatcher(INDEX_PAGE).forward(request,response);
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (this == o) result = true;
        if (o == null || getClass() != o.getClass()) return false;
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(INDEX_PAGE);
    }
}
