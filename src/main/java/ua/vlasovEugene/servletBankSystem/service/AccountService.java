package ua.vlasovEugene.servletBankSystem.service;

import ua.vlasovEugene.servletBankSystem.dao.IAccountDao;
import ua.vlasovEugene.servletBankSystem.dao.IAccountHistoryDao;
import ua.vlasovEugene.servletBankSystem.dao.IUserDao;
import ua.vlasovEugene.servletBankSystem.dao.daoFactory.AbstractDaoFactory;
import ua.vlasovEugene.servletBankSystem.entity.Account;
import ua.vlasovEugene.servletBankSystem.entity.PaymentHistory;
import ua.vlasovEugene.servletBankSystem.utils.TransactionHandler;
import ua.vlasovEugene.servletBankSystem.utils.exceptions.DaoException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class AccountService {
    private final AbstractDaoFactory FACTORY = AbstractDaoFactory.getDaoFactory("MY_SQL_FACTORY");
    private IUserDao userDao;
    private IAccountDao accountDao;
    private IAccountHistoryDao historyDao;
    private final String REFILL_ACCOUNT = "Account replenishment";

    public AccountService() {
        userDao = FACTORY.getUserDao();
        accountDao = FACTORY.getAccountDao();
        historyDao = FACTORY.getAccountHistoryDao();
    }

    public AccountService(IUserDao userDao,IAccountDao dao, IAccountHistoryDao historyDao) {
        this.userDao = userDao;
        this.accountDao = dao;
        this.historyDao = historyDao;
    }

    public void createNewAccount(Account newAccount) throws DaoException {
        TransactionHandler.runInTransaction(connection -> accountDao.addNewAccount(connection, newAccount));
    }

    public List<Long> getAllAccountsNumbers() throws DaoException {
        AtomicReference<List<Long>> accountNumbers = new AtomicReference<>();

        TransactionHandler.runInTransaction(connection -> accountNumbers.set(accountDao.getAllAccountNumbers(connection)));

        return accountNumbers.get();
    }

    public List<PaymentHistory> getHistoryOfCurrentAccount(Long accountNumber) throws DaoException {
        AtomicReference<List<PaymentHistory>> accountHistory = new AtomicReference<>();

        TransactionHandler.runInTransaction(connection ->
        accountHistory.set(historyDao.getHistoryOfCurrentAccount(connection, accountNumber))
        );

        return accountHistory.get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountService that = (AccountService) o;
        return FACTORY.equals(that.FACTORY) &&
                Objects.equals(userDao, that.userDao) &&
                Objects.equals(accountDao, that.accountDao) &&
                Objects.equals(historyDao, that.historyDao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(FACTORY, userDao, accountDao, historyDao);
    }

    public void refillCurrentAccount(Long accountNumber, BigDecimal summ) throws DaoException {
        TransactionHandler.runInTransaction(connection -> {

            Account currentAccount = accountDao.getCurrentAccount(connection,accountNumber);
            BigDecimal currentBalance = currentAccount.getCurrentBalance().add(summ);

            accountDao.changeBalanceOfCurrentAccount(connection, accountNumber,currentBalance);
            LocalDateTime currentDate = LocalDateTime.now();


            PaymentHistory newAction = new PaymentHistory();
            newAction.setCurrentBalance(currentBalance);
            newAction.setAccountNumber(accountNumber);
            newAction.setTransactionAmount(summ);
            newAction.setNotification(REFILL_ACCOUNT);
            newAction.setDateOfTransaction(currentDate);

            historyDao.addNewActionWithAccount(connection,newAction);
        });
    }

    public boolean recipientAccountIsExist(Long accountNumber) throws DaoException {
        AtomicBoolean result = new AtomicBoolean(false);

        TransactionHandler.runInTransaction(connection -> {
            if (accountDao.getCurrentAccount(connection, accountNumber) != null)
                result.set(true);
        });

        return result.get();
    }

    public boolean currentAccountHaveEnoughMoney(Long currentAccount, BigDecimal countOfMoney) throws DaoException {
        AtomicBoolean result = new AtomicBoolean(false);

        TransactionHandler.runInTransaction(connection -> {
            Account account = accountDao.getCurrentAccount(connection,currentAccount);

            if(account.getAccountType().equals("deposit")){
                BigDecimal totalBalance = account.getCurrentBalance();
                if (totalBalance.subtract(countOfMoney).signum() > 0)
                    result.set(true);
            }

            if(account.getAccountType().equals("credit")){
                BigDecimal totalBalance = account.getCurrentBalance().add(account.getCreditLimit());
                if (totalBalance.subtract(countOfMoney).signum() > 0)
                    result.set(true);
            }

        });

        return result.get();
    }

    public void sendMoneyToAcc(Long donorAccNumber, Long recipientAccNumber, BigDecimal countOfMoney) throws DaoException {
        TransactionHandler.runInTransaction(connection -> {

            Account donorAcc = accountDao.getCurrentAccount(connection,donorAccNumber);
            Account recipientAcc = accountDao.getCurrentAccount(connection,recipientAccNumber);

            BigDecimal donorBalance = donorAcc.getCurrentBalance().subtract(countOfMoney);
            BigDecimal recipientBalance = recipientAcc.getCurrentBalance().add(countOfMoney);

            accountDao.changeBalanceOfCurrentAccount(connection,donorAccNumber,donorBalance);
            accountDao.changeBalanceOfCurrentAccount(connection,recipientAccNumber,recipientBalance);

            PaymentHistory forDonorAcc = new PaymentHistory(
                    null,
                    donorAccNumber,
                    countOfMoney.negate(),
                    donorBalance,
                    LocalDateTime.now(),
                    String.format("Transfer to account No%s",recipientAccNumber)

            );

            PaymentHistory forRecipAcc = new PaymentHistory(
                    null,
                    recipientAccNumber,
                    countOfMoney,
                    recipientBalance,
                    LocalDateTime.now(),
                    String.format("Transfer from account No%s",donorAccNumber)
            );

            historyDao.addNewActionWithAccount(connection,forDonorAcc);
            historyDao.addNewActionWithAccount(connection,forRecipAcc);
        });
    }

    public void sendMoneyToAcc(Long donorAccNumber, String dummyRecipient, BigDecimal countOfMoney) throws DaoException {
        TransactionHandler.runInTransaction(connection -> {

            Account donorAcc = accountDao.getCurrentAccount(connection,donorAccNumber);

            BigDecimal donorBalance = donorAcc.getCurrentBalance().subtract(countOfMoney);

            accountDao.changeBalanceOfCurrentAccount(connection,donorAccNumber,donorBalance);

            PaymentHistory forDonorAcc = new PaymentHistory(
                    null,
                    donorAccNumber,
                    countOfMoney.negate(),
                    donorBalance,
                    LocalDateTime.now(),
                    String.format("Transfer to account No%s to another bank",dummyRecipient)

            );

            historyDao.addNewActionWithAccount(connection,forDonorAcc);
        });
    }
}
