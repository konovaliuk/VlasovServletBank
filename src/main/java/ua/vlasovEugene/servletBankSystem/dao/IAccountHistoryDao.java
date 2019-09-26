package ua.vlasovEugene.servletBankSystem.dao;

import ua.vlasovEugene.servletBankSystem.entity.PaymentHistory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Dao-interface for working with a table 'payment_history'
 */
public interface IAccountHistoryDao {

    /**
     * The method adds a history of a new completed action with the specified account
     *
     * @param connection     Used to contact the database
     * @param paymentHistory Entity with information about this action
     * @throws SQLException Will be thrown if something goes wrong
     */
    void addNewActionWithAccount(Connection connection, PaymentHistory paymentHistory) throws SQLException;

    /**
     * Returns the entire history of actions with this account
     *
     * @param connection    Used to contact the database
     * @param accountNumber Account number for which we need the whole story
     * @return List<PaymentHistory>
     * @throws SQLException Will be thrown if something goes wrong
     */
    List<PaymentHistory> getHistoryOfCurrentAccount(
            Connection connection, Long accountNumber, int ofset, int limit) throws SQLException;

    /**
     * @param connection Used to contact the database
     * @return total count of records in database
     * @throws SQLException Will be thrown if something goes wrong
     */
    int getTotalCountOfRecords(Connection connection) throws SQLException;
}
