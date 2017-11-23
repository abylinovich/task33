package by.epam.task33.service.impl.sax;

import by.epam.task33.dao.XMLDocumentDAO;
import by.epam.task33.dao.XMLDocumentDAOFactory;
import by.epam.task33.entity.Book;
import by.epam.task33.exception.ParseException;
import by.epam.task33.service.ParseService;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class SAXParseService implements ParseService {

    private static final String PARSE_EXCEPTION_MESSAGE = "File has wrong format.";
    private static final String FILE_NOT_FOUND_EXCEPTION_MESSAGE = "Cannot get access to file.";
    private static final String IO_EXCEPTION_MESSAGE = "Cannot read file.";

    private XMLDocumentDAO xmlDocumentDAO = XMLDocumentDAOFactory.getDocumentDAO();
    private List<Book> catalog = null;

    @Override
    public synchronized List<Book> parse() throws ParseException {
        if(catalog == null) {
            XMLReader reader;
            CatalogSAXHandler handler = new CatalogSAXHandler();
            try {
                reader = XMLReaderFactory.createXMLReader();
                reader.setContentHandler(handler);
                InputStream xmlDocumentInputStream = xmlDocumentDAO.getXMLDocumentInputStream();
                reader.parse(new InputSource(xmlDocumentInputStream));
            } catch (SAXException e) {
                throw new ParseException(PARSE_EXCEPTION_MESSAGE + " " + e.getMessage(), e);
            } catch (FileNotFoundException e) {
                throw new ParseException(FILE_NOT_FOUND_EXCEPTION_MESSAGE + " " + e.getMessage(), e);
            } catch (IOException e) {
                throw new ParseException(IO_EXCEPTION_MESSAGE + " " + e.getMessage(), e);
            }
            catalog = handler.getCatalog();
        }
        return catalog;
    }

}
