package ua.vlasovEugene.servletBankSystem.dao;

import ua.vlasovEugene.servletBankSystem.entity.CreditOpeningRequest;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Dao-interface for working with a table 'credit_opening_request'
 */
public interface ICreditRequestDao {
    List<CreditOpeningRequest> getAllUserRequests(Connection connection) throws SQLException;
}
