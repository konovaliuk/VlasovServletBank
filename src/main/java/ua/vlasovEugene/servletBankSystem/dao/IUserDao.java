package ua.vlasovEugene.servletBankSystem.dao;

import ua.vlasovEugene.servletBankSystem.entity.User;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Dao-interface for working with a table 'user'
 */
public interface IUserDao {

    boolean userByEmailIsExist(Connection connection, String login, String password) throws SQLException;

    User getUserByLoginAndPassword(Connection connection, User user)throws SQLException;

    void addNewUser(Connection connection, User newUser) throws SQLException;

    User getUserByLogin(Connection connection, String login) throws SQLException;
}
