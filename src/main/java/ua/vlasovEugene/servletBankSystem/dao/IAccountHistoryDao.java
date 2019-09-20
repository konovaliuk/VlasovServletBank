package ua.vlasovEugene.servletBankSystem.dao;

import ua.vlasovEugene.servletBankSystem.entity.PaymentHistory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Dao-interface for working with a table 'payment_history'
 */
public interface IAccountHistoryDao {
    void addNewActionWithAccount(Connection connection, PaymentHistory paymentHistory) throws SQLException;

    List<PaymentHistory> getHistoryOfCurrentAccount(Connection connection, Long accountNumber) throws SQLException;

}
