package ua.vlasovEugene.servletBankSystem.dao.daoFactory;

import ua.vlasovEugene.servletBankSystem.dao.IAccountDao;
import ua.vlasovEugene.servletBankSystem.dao.IAccountHistoryDao;
import ua.vlasovEugene.servletBankSystem.dao.ICreditRequestDao;
import ua.vlasovEugene.servletBankSystem.dao.IUserDao;

public abstract class AbstractDaoFactory {
    private static final String MY_SQL_FACTORY = "MY_SQL_FACTORY";

    public abstract IAccountHistoryDao getAccountHistoryDao();
    public abstract IAccountDao getAccountDao();
    public abstract IUserDao getUserDao();
    public abstract ICreditRequestDao getRequestDao();

    public static AbstractDaoFactory getDaoFactory(String whichFactory){
        AbstractDaoFactory result = null;

        if(whichFactory.equalsIgnoreCase(MY_SQL_FACTORY)){
            result = new MySqlFactoryDao();
        }

        return result;
    }
}
