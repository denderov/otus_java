package ru.otus.web.servlet;

import ru.otus.api.model.User;
import ru.otus.api.service.DBServiceUser;
import ru.otus.web.services.TemplateProcessor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class UserAddServlet extends HttpServlet {

    private static final String PARAM_USER_NAME = "name";
    private static final String PARAM_LOGIN = "login";
    private static final String PARAM_PASSWORD = "password";
    private static final String PAGE_TEMPLATE = "add_user.html";


    private final TemplateProcessor templateProcessor;
    private final DBServiceUser dbServiceUser;

    public UserAddServlet(TemplateProcessor templateProcessor, DBServiceUser dbServiceUser) {
        this.templateProcessor = templateProcessor;
        this.dbServiceUser = dbServiceUser;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.getWriter().println(templateProcessor.getPage(PAGE_TEMPLATE, Collections.emptyMap()));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String name = request.getParameter(PARAM_USER_NAME);
        String login = request.getParameter(PARAM_LOGIN);
        String password = request.getParameter(PARAM_PASSWORD);

        if (dbServiceUser.findByLogin(login).isEmpty()) {
            User newUser = new User(name, login, password);
            dbServiceUser.saveUser(newUser);

            response.sendRedirect("/users");
        }

    }

}
