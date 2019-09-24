package ua.vlasovEugene.servletBankSystem.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class LanguageChanger implements Command {
    private final String TARGETPAGE = "/";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String language = request.getParameter("language");
        request.getSession().setAttribute("currentLang", language);

        response.sendRedirect(TARGETPAGE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o != null && getClass() == o.getClass();
    }

    @Override
    public int hashCode() {
        return Objects.hash(TARGETPAGE);
    }
}
