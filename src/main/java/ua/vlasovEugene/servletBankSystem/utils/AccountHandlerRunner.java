package ua.vlasovEugene.servletBankSystem.utils;

import ua.vlasovEugene.servletBankSystem.service.InternalAccountHandler;
import ua.vlasovEugene.servletBankSystem.utils.exceptions.DaoException;

public class AccountHandlerRunner extends Thread {
    private InternalAccountHandler hadler;
    private Long SLEEP_ONE_DAY = (long) (1000 * 60 * 60 * 24);

    public AccountHandlerRunner() {
        hadler = new InternalAccountHandler();
        start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                hadler.doWork();
                Thread.sleep(SLEEP_ONE_DAY);
            } catch (DaoException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
