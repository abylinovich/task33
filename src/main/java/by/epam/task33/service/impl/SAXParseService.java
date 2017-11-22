package by.epam.task33.service.impl;

import by.epam.task33.dao.XMLDocumentDAO;
import by.epam.task33.dao.XMLDocumentDAOFactory;
import by.epam.task33.entity.Book;
import by.epam.task33.service.ParseService;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SAXParseService implements ParseService {

    private static final String ACCESS_EXCEPTION_MESSAGE = "Cannot get access to file.";

    private XMLDocumentDAO xmlDocumentDAO = XMLDocumentDAOFactory.getDocumentDAO();
    List<Book> catalog = null;

    @Override
    public synchronized List<Book> parse() throws SAXException {
        if(catalog == null) {
            XMLReader reader = XMLReaderFactory.createXMLReader();
            CatalogSAXHandler handler = new CatalogSAXHandler();
            reader.setContentHandler(handler);
            InputStream is = xmlDocumentDAO.getXMLDocumentInputStream();
            try {
                reader.parse(new InputSource(is));
            } catch (IOException e) {
                throw new SAXException(ACCESS_EXCEPTION_MESSAGE);
            }
            catalog = handler.getCatalog();
        }
        return catalog;

    }

}
