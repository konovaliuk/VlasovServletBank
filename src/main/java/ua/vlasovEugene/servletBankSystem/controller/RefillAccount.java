package ua.vlasovEugene.servletBankSystem.controller;

import ua.vlasovEugene.servletBankSystem.service.AccountService;
import ua.vlasovEugene.servletBankSystem.utils.exceptions.DaoException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

public class RefillAccount implements Command {
    private AccountService service;
    private final String ACCOUNTPAGE = "/WEB-INF/view/userhistorypage.jsp";

    public RefillAccount(AccountService service) {
        this.service = service;
    }

    public RefillAccount() {
        service = new AccountService();
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, DaoException {
        Long accountNumber = Long.valueOf((String) request.getSession().getAttribute("accountNumber"));
        BigDecimal summ = new BigDecimal(request.getParameter("summ").replace(',', '.'));

        service.refillCurrentAccount(accountNumber, summ);

        response.sendRedirect("/userpage/accountoperation");
    }
}
