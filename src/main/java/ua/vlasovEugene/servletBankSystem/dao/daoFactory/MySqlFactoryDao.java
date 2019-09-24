package ua.vlasovEugene.servletBankSystem.dao.daoFactory;

import ua.vlasovEugene.servletBankSystem.dao.*;

public class MySqlFactoryDao extends AbstractDaoFactory {

    @Override
    public IAccountHistoryDao getAccountHistoryDao() {
        return new MySqlAccountHistoryDao();
    }

    @Override
    public IAccountDao getAccountDao() {
        return new MySqlAccountDao();
    }

    @Override
    public IUserDao getUserDao() {
        return new MySqlUserDao();
    }

    @Override
    public ICreditRequestDao getRequestDao() {
        return new MySqlCreditRequestDao();
    }
}
