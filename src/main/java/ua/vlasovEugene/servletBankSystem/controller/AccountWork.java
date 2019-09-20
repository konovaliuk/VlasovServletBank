package ua.vlasovEugene.servletBankSystem.controller;

import ua.vlasovEugene.servletBankSystem.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class AccountWork implements Command {
    private AccountService service;
    private final String ADD_MONEY_PAGE = "/WEB-INF/view/addmoneypage.jsp";
    private final String SEND_CURRENT_BANK = "/WEB-INF/view/currentAccOperation.jsp";

    public AccountWork() {
        service = new AccountService();
    }

    public AccountWork(AccountService service) {
        this.service = service;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String typeOfWork = request.getParameter("typeofwork");

        switch (typeOfWork) {
            case "addmoney":
                request.getRequestDispatcher(ADD_MONEY_PAGE).forward(request, response);
                break;
            case "sendmoney":
                request.getRequestDispatcher(SEND_CURRENT_BANK).forward(request, response);
                break;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountWork that = (AccountWork) o;
        return Objects.equals(service, that.service);
    }

    @Override
    public int hashCode() {
        return Objects.hash(service);
    }
}
