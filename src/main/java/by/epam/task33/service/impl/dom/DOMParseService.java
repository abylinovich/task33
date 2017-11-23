package by.epam.task33.service.impl.dom;

import by.epam.task33.dao.XMLDocumentDAO;
import by.epam.task33.dao.XMLDocumentDAOFactory;
import by.epam.task33.entity.Book;
import by.epam.task33.exception.ParseException;
import by.epam.task33.service.ParseService;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.List;

public class DOMParseService implements ParseService {

    private static final String PARSE_EXCEPTION_MESSAGE = "File has wrong format.";
    private static final String FILE_NOT_FOUND_EXCEPTION_MESSAGE = "Cannot get access to file.";
    private static final String IO_EXCEPTION_MESSAGE = "Cannot read file.";

    private XMLDocumentDAO xmlDocumentDAO = XMLDocumentDAOFactory.getDocumentDAO();
    private List<Book> catalog = null;

    @Override
    public synchronized List<Book> parse() throws ParseException {
        if(catalog == null) {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder documentBuilder = dbFactory.newDocumentBuilder();
                InputStream xmlDocumentInputStream = xmlDocumentDAO.getXMLDocumentInputStream();
                Document document = documentBuilder.parse(xmlDocumentInputStream);
                CatalogDOMHandler handler = new CatalogDOMHandler();
                handler.parse(document);
                catalog = handler.getCatalog();
            } catch (ParserConfigurationException | SAXException e) {
                throw new ParseException(PARSE_EXCEPTION_MESSAGE + " " + e.getMessage(), e);
            } catch (FileNotFoundException e) {
                 throw new ParseException(FILE_NOT_FOUND_EXCEPTION_MESSAGE + " " + e.getMessage(), e);
            } catch (IOException e) {
                throw new ParseException(IO_EXCEPTION_MESSAGE + " " + e.getMessage(), e);
            }
        }
        return catalog;
    }

}
