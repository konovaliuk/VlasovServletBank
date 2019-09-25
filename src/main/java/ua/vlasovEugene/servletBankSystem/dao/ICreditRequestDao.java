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

    /**
     * Returns all credit account creation requests from all users
     *
     * @param connection Used to contact the database
     * @return List which contains all the requests
     * @throws SQLException Will be thrown if something goes wrong
     */
    List<CreditOpeningRequest> getAllUserRequests(Connection connection) throws SQLException;

    /**
     * Adds a new credit account request to the database
     *
     * @param connection     Used to contact the database
     * @param currentRequest Entity with new query parameters
     * @throws SQLException Will be thrown if something goes wrong
     */
    void addNewRequest(Connection connection, CreditOpeningRequest currentRequest) throws SQLException;

    /**
     * Deletes the specified credit account creation request from the database
     * @param connection Used to contact the database
     * @param currentRequest data on which the request will be found in the database and deleted
     * @throws SQLException Will be thrown if something goes wrong
     */
    void deleteCurrentRequest(Connection connection, CreditOpeningRequest currentRequest) throws SQLException;

    /**
     * Returns the request data for the creation of a credit account in the form of an entity
     * @param connection Used to contact the database
     * @param requestId ID of the desired request
     * @return Entity with request data
     * @throws SQLException Will be thrown if something goes wrong
     */
    CreditOpeningRequest getRequestById(Connection connection, Integer requestId) throws SQLException;
}
