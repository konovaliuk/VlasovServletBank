package ua.vlasovEugene.servletBankSystem.dao;

import ua.vlasovEugene.servletBankSystem.entity.CreditOpeningRequest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * implementation of the interface for working with MySql table
 * @see ua.vlasovEugene.servletBankSystem.dao.ICreditRequestDao
 */
public class MySqlCreditReuestDao implements ICreditRequestDao {
    private final String GET_ALL_USER_REQUESTS = "SELECT * FROM credit_opening_request";

    /**
     * A method that returns all requests for opening credit accounts from all users
     * @param connection input parameter for working with sql database.
     * @return All requests for opening credit accounts in the form of implementation List
     * @throws SQLException if something goes wrong this exception will be thrown
     */
    @Override
    public List<CreditOpeningRequest> getAllUserRequests(Connection connection) throws SQLException {
        List<CreditOpeningRequest> requests = new ArrayList<>();

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_USER_REQUESTS)) {

            while (resultSet.next()){
                CreditOpeningRequest request = new CreditOpeningRequest(
                        resultSet.getInt("request_ID"),
                        resultSet.getString("user_email_login"),
                        resultSet.getBigDecimal("percent_of_account")
                );
                requests.add(request);
            }
        }
        return requests;
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
        return Objects.hash(GET_ALL_USER_REQUESTS);
    }
}
