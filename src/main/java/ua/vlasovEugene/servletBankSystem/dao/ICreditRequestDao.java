package ua.vlasovEugene.servletBankSystem.dao;

import ua.vlasovEugene.servletBankSystem.entity.CreditOpeningRequest;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Dao-interface for working with a table 'credit_opening_request'
 */
public interface ICreditRequestDao {
    List<CreditOpeningRequest> getAllUserRequests(Connection connection) throws SQLException;

    void addNewRequest(Connection connection, CreditOpeningRequest currentRequest) throws SQLException;

    void deleteCurrentRequest(Connection connection, CreditOpeningRequest currentRequest) throws SQLException;

    CreditOpeningRequest getRequestById(Connection connection, Integer requestId) throws SQLException;
}
