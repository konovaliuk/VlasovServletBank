package ua.vlasovEugene.servletBankSystem.service;

import ua.vlasovEugene.servletBankSystem.dao.ICreditRequestDao;
import ua.vlasovEugene.servletBankSystem.dao.daoFactory.AbstractDaoFactory;
import ua.vlasovEugene.servletBankSystem.entity.CreditOpeningRequest;
import ua.vlasovEugene.servletBankSystem.utils.TransactionHandler;
import ua.vlasovEugene.servletBankSystem.utils.exceptions.DaoException;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class RequestService{

    private final AbstractDaoFactory FACTORY = AbstractDaoFactory.getDaoFactory("MY_SQL_FACTORY");
    private ICreditRequestDao dao;

    public RequestService() {
        dao = FACTORY.getRequestDao();
    }

    public RequestService(ICreditRequestDao dao) {
        this.dao = dao;
    }

    public List<CreditOpeningRequest> getAllRequests() throws DaoException {
        AtomicReference<List<CreditOpeningRequest>> requests = new AtomicReference<>();

        TransactionHandler.runInTransaction(connection -> requests.set(dao.getAllUserRequests(connection)));

        return requests.get();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestService that = (RequestService) o;
        return Objects.equals(dao, that.dao);
    }

    public int hashCode() {
        return Objects.hash(dao);
    }
}
