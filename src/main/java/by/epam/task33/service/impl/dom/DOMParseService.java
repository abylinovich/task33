package by.epam.task33.service.impl.dom;

import by.epam.task33.dao.XMLDocumentDAO;
import by.epam.task33.dao.XMLDocumentDAOFactory;
import by.epam.task33.entity.Book;
import by.epam.task33.exception.ParseException;
import by.epam.task33.service.ParseService;
import by.epam.task33.service.impl.stax.CatalogSTAXHandler;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class DOMParseService implements ParseService {

//    private static final String PARSE_EXCEPTION_MESSAGE = "File has wrong format.";
//    private static final String FILE_NOT_FOUND_EXCEPTION_MESSAGE = "Cannot get access to file.";
//    private static final String IO_EXCEPTION_MESSAGE = "Cannot read file.";

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


            } catch (Exception e) {
                // TODO: 23.11.2017
                throw new ParseException();
            }
        }
        return catalog;
    }

}
