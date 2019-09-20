package ua.vlasovEugene.servletBankSystem.controller;

import ua.vlasovEugene.servletBankSystem.service.RequestService;
import ua.vlasovEugene.servletBankSystem.utils.exceptions.DaoException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

public class GetAdminPage implements Command {
    private RequestService service;
    private final String ADMINPAGE =  "/WEB-INF/view/adminpage.jsp";

    public GetAdminPage() {
        service = new RequestService();
    }

    public GetAdminPage(RequestService service) {
        this.service = service;
    }

    /**
     * Before moving to the admin page for the admin, a list of all applications for opening credit accounts
     * from all users is formed and displayed in the table
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        try {
            session.setAttribute("allUserRequests", service.getAllRequests());
        } catch (DaoException e) {
            request.setAttribute("exceptionError", e.getMessage());
        } finally {
            request.getRequestDispatcher(ADMINPAGE).forward(request,response);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetAdminPage that = (GetAdminPage) o;
        return Objects.equals(service, that.service);
    }

    @Override
    public int hashCode() {
        return Objects.hash(service, ADMINPAGE);
    }
}
