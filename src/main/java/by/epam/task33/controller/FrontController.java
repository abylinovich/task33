package by.epam.task33.controller;

import by.epam.task33.controller.command.CommandFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {

    private static final String COMMAND_ATTRIBUTE = "command";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getParameter(COMMAND_ATTRIBUTE);
        CommandFactory.getCommand(commandName).process(getServletContext(),request, response);
    }

}
