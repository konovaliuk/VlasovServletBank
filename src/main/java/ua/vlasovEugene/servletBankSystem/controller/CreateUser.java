package ua.vlasovEugene.servletBankSystem.controller;

import ua.vlasovEugene.servletBankSystem.service.*;
import ua.vlasovEugene.servletBankSystem.utils.exceptions.DaoException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class CreateUser implements Command {
    private UserService userService;
    private final String NEW_USER_PAGE = "/WEB-INF/view/newuser.jsp";

    public CreateUser() {
        userService = new UserService();
    }

    public CreateUser(UserService userService) {
        this.userService = userService;
    }

    /**
     * The method creates a new user,
     * a new deposit account for this user,
     * a new event about account creation
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("email");
        String password = request.getParameter("password");


        try {
            if(!(userService.currentUSerIsExist(login,password))){
                Map<String,String> parameters = new HashMap<>();

                parameters.put("login",login);
                parameters.put("password",password);
                parameters.put("firstname",request.getParameter("firstname"));
                parameters.put("lastname",request.getParameter("lastname"));
                parameters.put("firstpal",request.getParameter("firstpal"));

                userService.addNewUser(parameters);

                request.setAttribute("successOperation",true);
            } else {
                request.setAttribute("userisexist",true);
            }
        } catch (DaoException e) {
            request.setAttribute("exeptionError", e.getMessage());
        } finally {
            request.getRequestDispatcher(NEW_USER_PAGE).forward(request,response);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateUser that = (CreateUser) o;
        return Objects.equals(userService, that.userService);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userService, NEW_USER_PAGE);
    }
}
