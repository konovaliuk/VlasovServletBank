package ua.vlasovEugene.servletBankSystem.dao;

import ua.vlasovEugene.servletBankSystem.entity.Account;
import ua.vlasovEugene.servletBankSystem.entity.User;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * The interface for working with the "account" table.
 * Uses connection and entity 'Account' and sometimes 'User'
 * */
public interface IAccountDao {

    List<Long> getAllAccountNumbers(Connection connection) throws SQLException;

    void addNewAccount(Connection connection, Account newAccount) throws SQLException;

    Account getCurrentAccount(Connection connection, Long accountNumber) throws SQLException;

    void changeBalanceOfCurrentAccount(Connection connection, Long accountNumber, BigDecimal currentBalance) throws SQLException;

    Map<String, List<Account>> getAllAccountsOfCurrentUser(Connection connection, User currentUser) throws SQLException;
}
