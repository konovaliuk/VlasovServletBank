package ua.vlasovEugene.servletBankSystem.controller;

import ua.vlasovEugene.servletBankSystem.service.AccountService;
import ua.vlasovEugene.servletBankSystem.utils.exceptions.DaoException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;

public class MoneySender implements Command {
    private AccountService service;
    private final String SUCCESS_PAGE = "/WEB-INF/view/userhistorypage.jsp";
    private final String ERROR_PAGE = "/WEB-INF/view/currentBankOperation.jsp";

    public MoneySender() {
        service = new AccountService();
    }

    public MoneySender(AccountService service) {
        this.service = service;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long currentAccount = Long.valueOf((String) request.getSession().getAttribute("accountNumber"));
        BigDecimal countOfMoney = new BigDecimal(request.getParameter("money").replace(',','.'));
        Long recipientAccountNumber = Long.valueOf(request.getParameter("account"));
        boolean intrabankOperation = request.getParameter("typeOfTransaction").equals("currentBank");

        String targetPage = SUCCESS_PAGE;
        try{
            if(service.currentAccountHaveEnoughMoney(currentAccount,countOfMoney)){

                if(intrabankOperation){
                    if(service.recipientAccountIsExist(recipientAccountNumber)){
                        service.sendMoneyToAcc(currentAccount, recipientAccountNumber, countOfMoney);
                        request.setAttribute("sucessOperation", true);
                        request.setAttribute("history", service.getHistoryOfCurrentAccount(currentAccount));
                    } else {
                        request.setAttribute("noExistAccount", true);
                        targetPage = ERROR_PAGE;
                    }
                } else {
                    String dummyRecipient = String.valueOf(recipientAccountNumber);
                    service.sendMoneyToAcc(currentAccount, dummyRecipient, countOfMoney);
                    request.setAttribute("sucessOperation", true);
                    request.setAttribute("history", service.getHistoryOfCurrentAccount(currentAccount));
                }


            } else {
                request.setAttribute("noEnoughMoney", true);
                targetPage = ERROR_PAGE;
            }
        } catch (DaoException e){
            e.printStackTrace();
            request.setAttribute("exceptionError",e.getMessage());
        }

        request.getRequestDispatcher(targetPage).forward(request, response);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoneySender that = (MoneySender) o;
        return service.equals(that.service);
    }

    @Override
    public int hashCode() {
        return Objects.hash(service);
    }
}
