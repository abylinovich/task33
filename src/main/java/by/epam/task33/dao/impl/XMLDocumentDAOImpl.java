package by.epam.task33.dao.impl;

import by.epam.task33.dao.XMLDocumentDAO;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class XMLDocumentDAOImpl implements XMLDocumentDAO {

    private static final String FILE_PATH = "Catalog.xml";
    private static final String FILE_NOT_FOUND_EXCEPTION_MESSAGE = "XML file not found.";

    @Override
    public InputStream getXMLDocumentInputStream() throws FileNotFoundException {
        InputStream is = getClass().getClassLoader().getResourceAsStream(FILE_PATH);
        if(is != null) {
            return is;
        } else {
            throw new FileNotFoundException(FILE_NOT_FOUND_EXCEPTION_MESSAGE);
        }
    }

}
