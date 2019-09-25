package ua.vlasovEugene.servletBankSystem.controller.servlets;

import org.apache.log4j.Logger;
import ua.vlasovEugene.servletBankSystem.controller.*;
import ua.vlasovEugene.servletBankSystem.utils.exceptions.DaoException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(FrontController.class);
    private final String ERRORPAGE = "/WEB-INF/view/errorpage.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doWork(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doWork(req,resp);

    }

    private void doWork(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command;
        if (req.getParameter("command") != null)
            command = req.getParameter("command");
        else {
            command = req.getRequestURI();
        }

        LOG.info(String.format("Command %s received", command));
        Command currentCommand = CommandContainer.getCommand(command);
        try {
            currentCommand.execute(req, resp);
        } catch (DaoException e) {
            e.printStackTrace();//todo обработать ошибку
            req.setAttribute("daoException", e);
            req.getRequestDispatcher(ERRORPAGE).forward(req, resp);
        }
    }
}
