package ua.vlasovEugene.servletBankSystem.service;

import ua.vlasovEugene.servletBankSystem.dao.*;
import ua.vlasovEugene.servletBankSystem.dao.daoFactory.AbstractDaoFactory;
import ua.vlasovEugene.servletBankSystem.entity.Account;
import ua.vlasovEugene.servletBankSystem.entity.PaymentHistory;
import ua.vlasovEugene.servletBankSystem.entity.User;
import ua.vlasovEugene.servletBankSystem.utils.TransactionHandler;
import ua.vlasovEugene.servletBankSystem.utils.exceptions.DaoException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class UserService {
    private final AbstractDaoFactory FACTORY = AbstractDaoFactory.getDaoFactory("MY_SQL_FACTORY");
    private final BigDecimal DEPOSIT_INTEREST_RATE = new BigDecimal("5");
    private final String FIRST_NOTE_FOR_PAYMENT_HISTORY = "Your account was created, congratulations !";
    private IAccountDao accountDao;
    private IUserDao userDao;
    private IAccountHistoryDao accHistoryDao;

    public UserService() {
        accountDao = FACTORY.getAccountDao();
        userDao = FACTORY.getUserDao();
        accHistoryDao = FACTORY.getAccountHistoryDao();
    }

    public UserService(IAccountDao accountDao, IUserDao userDao, IAccountHistoryDao accHistoryDao) {
        this.accountDao = accountDao;
        this.userDao = userDao;
        this.accHistoryDao = accHistoryDao;
    }

    public boolean currentUSerIsExist(String login, String password) throws DaoException {
        AtomicBoolean result = new AtomicBoolean(false);

        TransactionHandler.runInTransaction(connection -> {
            result.set(userDao.userByEmailIsExist(connection,login,password));
        });

        return result.get();
    }

    public Map<String,List<Account>> getAllCurrentUserAccounts(User currentUser) throws DaoException {
        AtomicReference<Map<String, List<Account>>> result = new AtomicReference<>();

        TransactionHandler.runInTransaction(connection -> {
            result.set(accountDao.getAllAccountsOfCurrentUser(connection, currentUser));
        });

        return result.get();
    }

    public void addNewUser(Map<String,String> parameters) throws DaoException {
        TransactionHandler.runInTransaction(connection -> {
            BigDecimal firstPal = new BigDecimal(parameters.get("firstpal").replace(',','.'));

            User newUser = createNewUser(parameters.get("firstname"),
                    parameters.get("lastname"),
                    parameters.get("login"),
                    parameters.get("password"));

            Account newAccount = createNewDepositAccount(connection,
                    parameters.get("login"),
                    firstPal);

            PaymentHistory firstAction = createFirstAction(newAccount.getAccountNumber(),
                    firstPal);

            userDao.addNewUser(connection,newUser);
            accountDao.addNewAccount(connection,newAccount);
            accHistoryDao.addNewActionWithAccount(connection,firstAction);
        });
    }

    private PaymentHistory createFirstAction(long accountNumber, BigDecimal firstpal) {
        LocalDateTime currentDate = LocalDateTime.now();

        PaymentHistory result = new PaymentHistory();
        result.setAccountNumber(accountNumber);
        result.setTransactionAmount(firstpal);
        result.setCurrentBalance(firstpal);
        result.setDateOfTransaction(currentDate);
        result.setNotification(FIRST_NOTE_FOR_PAYMENT_HISTORY);

        return result;
    }

    public Account createNewDepositAccount(Connection connection, String login, BigDecimal firstpal) throws SQLException {
        LocalDateTime currentDate = LocalDateTime.now();

        Account result = new Account();
        result.setAccountOwner(login);
        result.setAccountNumber(createAccountNumber(connection));
        result.setAccountType("deposit");
        result.setCurrentBalance(firstpal);
        result.setInterestRate(DEPOSIT_INTEREST_RATE);
        result.setCreditLimit(new BigDecimal("0"));
        result.setAccountValidity(currentDate);

        return result;
    }

    private long createAccountNumber(Connection connection) throws SQLException {
        List<Long> allAccountsNumbers = accountDao.getAllAccountNumbers(connection);
        int endOfRange = 999999999;
        int startOfRange = 99999999;

        Random random = new Random();
        int range = endOfRange - startOfRange+1;
        long accountNumber = random.nextInt(range)+1;

        while (allAccountsNumbers.contains(accountNumber)){
            accountNumber = random.nextInt(range)+1;
        }

        return accountNumber;
    }

    private User createNewUser(String firstname, String lastname, String login, String password) {
        User result = new User();

        result.setUserFirstname(firstname);
        result.setUserLastname(lastname);
        result.setUserLoginEmail(login);
        result.setUserPassword(password);
        result.setUserRole("user");
        result.setUserHaveCreditAcc(false);

        return result;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserService that = (UserService) o;
        return Objects.equals(accountDao, that.accountDao) &&
                Objects.equals(userDao, that.userDao) &&
                Objects.equals(accHistoryDao, that.accHistoryDao);
    }

    public int hashCode() {
        return Objects.hash(accountDao, userDao, accHistoryDao);
    }
}
