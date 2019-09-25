package ua.vlasovEugene.servletBankSystem.dao;

import ua.vlasovEugene.servletBankSystem.entity.User;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Dao-interface for working with a table 'user'
 */
public interface IUserDao {

    /**
     * The method checks for the existence of a user with such a login and password
     *
     * @param connection Used to contact the database
     * @param login      User login
     * @param password   User password
     * @return 'true' or 'false' whether such a user exists or not
     * @throws SQLException Will be thrown if something goes wrong
     */
    boolean userByEmailIsExist(Connection connection, String login, String password) throws SQLException;

    /**
     * Returns a user with a specific username and password
     *
     * @param connection Used to contact the database
     * @param user       Entity with the desired username and password
     * @return Entity with all data
     * @throws SQLException Will be thrown if something goes wrong
     */
    User getUserByLoginAndPassword(Connection connection, User user) throws SQLException;

    /**
     * Adds a new user to the database
     *
     * @param connection Used to contact the database
     * @param newUser    The entity to be added to the database
     * @throws SQLException Will be thrown if something goes wrong
     */
    void addNewUser(Connection connection, User newUser) throws SQLException;

    /**
     * Returns the user with the specified login
     * @param connection Used to contact the database
     * @param login User login
     * @return Entity with all data
     * @throws SQLException Will be thrown if something goes wrong
     */
    User getUserByLogin(Connection connection, String login) throws SQLException;

    /**
     * Changes the status that indicates whether the user has sent a request to create a credit account
     * @param connection Used to contact the database
     * @param userLoginEmail User login
     * @param requestStatusFlag Status indicator
     * @throws SQLException Will be thrown if something goes wrong
     */
    void changeCreditRequestStatusOfCurrentUser(Connection connection, String userLoginEmail, Boolean requestStatusFlag) throws SQLException;

    /**
     * Changes the status saying that the user has a credit account or not
     * @param connection Used to contact the database
     * @param userLoginEmail User login
     * @param isUserHaveCreditAcc Status indicator
     * @throws SQLException Will be thrown if something goes wrong
     */
    void changeCreditStatusOfCurrentUser(Connection connection, String userLoginEmail, Boolean isUserHaveCreditAcc) throws SQLException;
}
