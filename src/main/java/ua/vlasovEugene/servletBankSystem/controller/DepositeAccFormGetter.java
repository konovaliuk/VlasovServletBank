package ua.vlasovEugene.servletBankSystem.controller;

import ua.vlasovEugene.servletBankSystem.utils.exceptions.DaoException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DepositeAccFormGetter implements ua.vlasovEugene.servletBankSystem.controller.Command {
    private final String TARGETPAGE = "/WEB-INF/view/newDepositAcc.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, DaoException {
        request.getRequestDispatcher(TARGETPAGE).forward(request, response);
    }
}
