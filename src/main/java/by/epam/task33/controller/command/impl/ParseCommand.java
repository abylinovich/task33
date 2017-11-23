package by.epam.task33.controller.command.impl;

import by.epam.task33.controller.command.Command;
import by.epam.task33.entity.Book;
import by.epam.task33.exception.ParseException;
import by.epam.task33.service.ParseService;
import by.epam.task33.service.ParseServiceFactory;
import org.xml.sax.SAXException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ParseCommand implements Command {

    private static final String PARSER_NAME_ATTRIBUTE = "parserName";
    private static final String CATALOG_ATTRIBUTE = "catalog";
    private static final String PAGE_NUMBER_ATTRIBUTE = "pageNumber";
    private static final String ERROR_MESSAGE_ATTRIBUTE = "errorMessage";
    private static final String FIRST_PAGE = "1";
    private static final String PAGINATION_JSP_PATH = "/jsp/pagination.jsp";
    private static final String ERROR_PAGE_PATH = "/jsp/error.jsp";
    private static final String RENDER_PAGE_ERROR = "Cannot render page.";

    @Override
    public void process(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String parserName = request.getParameter(PARSER_NAME_ATTRIBUTE);
        ParseService parseService = ParseServiceFactory.getParseService(parserName);
        try {
            List<Book> catalog = parseService.parse();
            request.setAttribute(CATALOG_ATTRIBUTE, catalog);
            String pageNumber = request.getParameter(PAGE_NUMBER_ATTRIBUTE);
            if (pageNumber != null) {
                request.setAttribute(PAGE_NUMBER_ATTRIBUTE, pageNumber);
            } else {
                request.setAttribute(PAGE_NUMBER_ATTRIBUTE, FIRST_PAGE);
            }
            request.getRequestDispatcher(PAGINATION_JSP_PATH).forward(request, response);
        } catch (ParseException e) {
            request.setAttribute(ERROR_MESSAGE_ATTRIBUTE, RENDER_PAGE_ERROR + " " + e.getMessage());
            request.getRequestDispatcher(ERROR_PAGE_PATH).forward(request, response);
        }
    }

}
