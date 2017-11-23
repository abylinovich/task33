package by.epam.task33.service.impl.stax;

import by.epam.task33.dao.XMLDocumentDAO;
import by.epam.task33.dao.XMLDocumentDAOFactory;
import by.epam.task33.entity.Book;
import by.epam.task33.exception.ParseException;
import by.epam.task33.service.ParseService;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.util.List;

public class STAXParseService implements ParseService {

    private static final String FILE_NOT_FOUND_EXCEPTION_MESSAGE = "Cannot get access to file.";
    private static final String PARSE_EXCEPTION_MESSAGE = "File has wrong format.";

    private XMLDocumentDAO xmlDocumentDAO = XMLDocumentDAOFactory.getDocumentDAO();
    private List<Book> catalog = null;

    @Override
    public synchronized List<Book> parse() throws ParseException {
        if(catalog == null) {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            try {
                InputStream xmlDocumentInputStream = xmlDocumentDAO.getXMLDocumentInputStream();
                XMLEventReader eventReader = factory.createXMLEventReader(new InputStreamReader(xmlDocumentInputStream));
                CatalogSTAXHandler handler = new CatalogSTAXHandler();
                handler.parse(eventReader);
                catalog = handler.getCatalog();
            } catch (XMLStreamException e) {
                throw new ParseException(PARSE_EXCEPTION_MESSAGE + " " + e.getMessage(), e);
            } catch (FileNotFoundException e) {
                throw new ParseException(FILE_NOT_FOUND_EXCEPTION_MESSAGE + " " + e.getMessage(), e);
            }
        }
        return catalog;
    }

}
