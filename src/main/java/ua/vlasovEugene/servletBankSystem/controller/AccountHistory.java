package ua.vlasovEugene.servletBankSystem.controller;

import ua.vlasovEugene.servletBankSystem.service.AccountService;
import ua.vlasovEugene.servletBankSystem.utils.exceptions.DaoException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class AccountHistory implements Command {
    private AccountService service;
    private final String ACOOUNT_PAGE = "/WEB-INF/view/userhistorypage.jsp";

    public AccountHistory() {
        service = new AccountService();
    }

    public AccountHistory(AccountService service) {
        this.service = service;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long accountNumber = Long.valueOf(request.getParameter("accountNumber"));
        try {
            request.setAttribute("history",
                    service.getHistoryOfCurrentAccount(accountNumber));

            request.getSession().setAttribute("accountNumber", String.valueOf(accountNumber));

        } catch (DaoException e) {
            e.printStackTrace();
            request.setAttribute("exceptionError",e.getMessage());
        }
        request.getRequestDispatcher(ACOOUNT_PAGE).forward(request,response);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountHistory that = (AccountHistory) o;
        return Objects.equals(service, that.service);
    }

    @Override
    public int hashCode() {
        return Objects.hash(service, ACOOUNT_PAGE);
    }
}
