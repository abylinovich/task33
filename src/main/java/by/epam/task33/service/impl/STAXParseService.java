package by.epam.task33.service.impl;

import by.epam.task33.dao.XMLDocumentDAO;
import by.epam.task33.dao.XMLDocumentDAOFactory;
import by.epam.task33.entity.Book;
import by.epam.task33.exception.ParseException;
import by.epam.task33.service.ParseService;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.*;
import java.util.Iterator;
import java.util.List;

public class STAXParseService implements ParseService {

//    private static final String ACCESS_EXCEPTION_MESSAGE = "Cannot get access to file.";


    private XMLDocumentDAO xmlDocumentDAO = XMLDocumentDAOFactory.getDocumentDAO();
    List<Book> catalog = null;

    @Override
    public synchronized List<Book> parse() throws ParseException {
        if(catalog == null) {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            try {
                InputStream xmlDocumentInputStream = xmlDocumentDAO.getXMLDocumentInputStream();
                XMLEventReader eventReader = factory.createXMLEventReader(new InputStreamReader(xmlDocumentInputStream));
                CatalogSTAXHandler catalogSTAXHandler = new CatalogSTAXHandler(eventReader);
                catalogSTAXHandler.parse();
//                catalog = handler.getCatalog();
            } catch (XMLStreamException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                throw new ParseException();
            }


        }
        return catalog;
    }

}
