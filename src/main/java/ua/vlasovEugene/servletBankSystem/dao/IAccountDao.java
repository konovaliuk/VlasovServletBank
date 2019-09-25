package ua.vlasovEugene.servletBankSystem.dao;

import ua.vlasovEugene.servletBankSystem.entity.Account;
import ua.vlasovEugene.servletBankSystem.entity.User;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * The interface for working with the "account" table.
 * Uses connection and entity 'Account' and sometimes 'User'
 * */
public interface IAccountDao {

    /**
     * @param connection Used to work with the database
     * @return ArrayList(Long) with numbers of existing accounts
     * @throws SQLException It will be thrown in case of a database error
     */
    List<Long> getAllAccountNumbers(Connection connection) throws SQLException;

    /**
     * Add new Account-entity to database
     *
     * @param connection Used to work with the database
     * @param newAccount The entity to be added to the database
     * @throws SQLException It will be thrown in case of a database error
     */
    void addNewAccount(Connection connection, Account newAccount) throws SQLException;

    /**
     *
     * @param connection Used to work with the database
     * @param accountNumber parameter to get the desired account
     * @return account with current account number
     * @throws SQLException It will be thrown in case of a database error
     */
    Account getCurrentAccount(Connection connection, Long accountNumber) throws SQLException;

    /**
     * Changes the balance of the specified account
     * @param connection Used to work with the database
     * @param accountNumber Number of current account
     * @param currentBalance Expected Balance
     * @throws SQLException It will be thrown in case of a database error
     */
    void changeBalanceOfCurrentAccount(Connection connection, Long accountNumber, BigDecimal currentBalance) throws SQLException;

    /**
     *
     * @param connection Used to work with the database
     * @param currentUser The user of the account whom we want to receive
     * @return A HashMap of all types of accounts of the specified user
     * @throws SQLException It will be thrown in case of a database error
     */
    Map<String, List<Account>> getAllAccountsOfCurrentUser(Connection connection, User currentUser) throws SQLException;

    /**
     *
     * @param connection Used to work with the database
     * @param user The user whose balance we need
     * @param afterSixMonts If the validity period of the user’s account is longer than this date,
     *                      the amount of the account’s deposit will be added to the total result
     * @return the total balance of the specified user in six months
     * @throws SQLException It will be thrown in case of a database error
     */
    BigDecimal getTotalUsersBalanceAfter6Year(Connection connection, User user, LocalDateTime afterSixMonts) throws SQLException;
}
