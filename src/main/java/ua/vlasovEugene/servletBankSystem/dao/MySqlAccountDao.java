package ua.vlasovEugene.servletBankSystem.dao;

import ua.vlasovEugene.servletBankSystem.entity.Account;
import ua.vlasovEugene.servletBankSystem.entity.User;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * The class inheriting interface "IAccountDao".
 * Implements work with table "account" in MySql database
 * @see ua.vlasovEugene.servletBankSystem.dao.IAccountDao
 * */
public class MySqlAccountDao implements IAccountDao {
    private final String GET_ALL_ACCOUNTS_OF_CURRENT_USER = "SELECT * FROM account WHERE account_owner = ?";
    private final String GET_ALL_ACCOUNT_NUMBERS = "SELECT account_number FROM account";
    private final String ADD_NEW_ACCOUNT = "INSERT INTO account VALUES (null,?,?,?,?,?,?,?,?)";
    private final String GET_CURRENT_ACCOUNT = "SELECT * FROM account WHERE account_number = ?";
    private final String CHANGE_BALANCE_OF_CURRENT_ACC = "UPDATE account SET current_balance=? WHERE account_number = ?";


    /**
     * This is the method that returns all account numbers.
     * @param connection input parameter for working with sql database.
     * @return returns a collection of accountNumbers in the form of an implementation List.
     * @throws SQLException if something goes wrong this exception will be thrown.
     * */
    @Override
    public List<Long> getAllAccountNumbers(Connection connection) throws SQLException {
        List<Long> accountNumbers = new ArrayList<>();

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_ACCOUNT_NUMBERS)){
            while (resultSet.next()){
                long accountNumber = resultSet.getLong(1);
                accountNumbers.add(accountNumber);
            }
        }
        return accountNumbers;
    }

    /**
     * The method adds a new account to the corresponding table
     * @param connection input parameter for working with sql database.
     * @param newAccount Entity class with data to be added to the table.
     * @throws SQLException if something goes wrong this exception will be thrown
     * */
    @Override
    public void addNewAccount(Connection connection, Account newAccount) throws SQLException {

        try(PreparedStatement statement = connection.prepareStatement(ADD_NEW_ACCOUNT)){
            statement.setString(1,newAccount.getAccountOwner());
            statement.setLong(2,newAccount.getAccountNumber());
            statement.setObject(3,newAccount.getAccountType());
            statement.setBigDecimal(4,newAccount.getCurrentBalance());
            statement.setBigDecimal(5,newAccount.getInterestRate());
            statement.setBigDecimal(6,newAccount.getCreditLimit());
            statement.setTimestamp(7, Timestamp.valueOf(newAccount.getAccountValidity()));
            statement.setBigDecimal(8, newAccount.getDeposit());
            statement.executeUpdate();
        }
    }

    @Override
    public Account getCurrentAccount(Connection connection, Long accountNumber) throws SQLException {
        Account result = null;

        try(PreparedStatement statement = connection.prepareStatement(GET_CURRENT_ACCOUNT)){
            statement.setLong(1,accountNumber);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                result = new Account();
                result.setAccountNumber(resultSet.getLong("account_number"));
                result.setCurrentBalance(resultSet.getBigDecimal("current_balance"));
                result.setAccountType(resultSet.getString("account_type"));
                result.setDeposit(resultSet.getBigDecimal("deposit"));
                result.setCreditLimit(resultSet.getBigDecimal("credit_limit"));
            }
        }
        return result;
    }

    @Override
    public void changeBalanceOfCurrentAccount(Connection connection, Long accountNumber, BigDecimal currentBalance)
            throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(CHANGE_BALANCE_OF_CURRENT_ACC)){
            statement.setBigDecimal(1,currentBalance);
            statement.setLong(2,accountNumber);
            statement.executeUpdate();
        }
    }
    @Override
    public Map<String,List<Account>> getAllAccountsOfCurrentUser(Connection connection, User currentUser) throws SQLException {
        Map<String, List<Account>> accounts = new HashMap<>();
        List<Account>creditAcc = new ArrayList<>();
        List<Account>depAcc = new ArrayList<>();

        try(PreparedStatement statement = connection.prepareStatement(GET_ALL_ACCOUNTS_OF_CURRENT_USER)){
            statement.setString(1,currentUser.getUserLoginEmail());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Account account = new Account(
                        resultSet.getInt("account_ID"),
                        resultSet.getString("account_owner"),
                        resultSet.getLong("account_number"),
                        resultSet.getString("account_type"),
                        resultSet.getBigDecimal("current_balance"),
                        resultSet.getBigDecimal("interest_rate"),
                        resultSet.getBigDecimal("credit_limit"),
                        resultSet.getTimestamp("account_validity").toLocalDateTime(),
                        resultSet.getBigDecimal("deposit")
                );
                if(account.getAccountType().equals("credit")){
                    creditAcc.add(account);
                } else if (account.getAccountType().equals("deposit")){
                    depAcc.add(account);
                }
            }
        }
        accounts.put("creditAccounts",creditAcc);
        accounts.put("depositAccounts",depAcc);

        return accounts;
    }

    @Override
    public BigDecimal getTotalUsersBalanceAfter6Year(Connection connection, User user, LocalDateTime afterSixMonts) throws SQLException {
        BigDecimal result = new BigDecimal("0");

        Timestamp after6Monts = Timestamp.valueOf(afterSixMonts);

        try (PreparedStatement statement = connection.prepareStatement(
                GET_ALL_ACCOUNTS_OF_CURRENT_USER + "AND account_type = 'deposit' AND account_validity > ?")) {

            statement.setString(1, user.getUserLoginEmail());
            statement.setTimestamp(2, after6Monts);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getBigDecimal("deposit"));
                result = result.add(resultSet.getBigDecimal("deposit"));
            }
        }
        return result;
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
        return Objects.hash(GET_ALL_ACCOUNTS_OF_CURRENT_USER, GET_ALL_ACCOUNT_NUMBERS, ADD_NEW_ACCOUNT);
    }

}