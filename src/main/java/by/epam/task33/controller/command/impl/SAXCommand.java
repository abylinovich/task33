package by.epam.task33.controller.command.impl;

import by.epam.task33.controller.command.Command;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SAXCommand implements Command {

    @Override
    public void process(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/page.jsp").forward(request, response);
    }

}
