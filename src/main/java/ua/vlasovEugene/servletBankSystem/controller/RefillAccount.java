package ua.vlasovEugene.servletBankSystem.controller;

import ua.vlasovEugene.servletBankSystem.service.AccountService;
import ua.vlasovEugene.servletBankSystem.utils.exceptions.DaoException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;

public class RefillAccount implements Command {
    private AccountService service;
    private final String CURRENT_ACCOUNT_PAGE = "/WEB-INF/view/userhistorypage.jsp";
    private final String ERROR_PAGE = "/WEB-INF/view/addmoneypage.jsp";

    public RefillAccount() {
        service = new AccountService();
    }

    public RefillAccount(AccountService service) {
        this.service = service;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        BigDecimal summ = new BigDecimal(request.getParameter("summ").replace(',','.'));
        Long accountNumber = Long.valueOf((String) request.getSession().getAttribute("accountNumber"));

        try {
            service.refillCurrentAccount(accountNumber,summ);
            request.setAttribute("sucessOperation", true);
            request.setAttribute("history",service.getHistoryOfCurrentAccount(accountNumber));
            request.getRequestDispatcher(CURRENT_ACCOUNT_PAGE).forward(request,response);

        } catch (DaoException e) {
            request.setAttribute("exception", e.getMessage());
            request.getRequestDispatcher(ERROR_PAGE).forward(request,response);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RefillAccount that = (RefillAccount) o;
        return service.equals(that.service);
    }

    @Override
    public int hashCode() {
        return Objects.hash(service);
    }
}
