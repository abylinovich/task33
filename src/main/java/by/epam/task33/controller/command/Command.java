package by.epam.task33.controller.command;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {

    void process(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
