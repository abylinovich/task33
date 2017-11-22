package by.epam.task33.dao.impl;

import by.epam.task33.dao.XMLDocumentDAO;

import java.io.InputStream;

public class XMLDocumentDAOImpl implements XMLDocumentDAO {

    private static final String FILE_PATH = "Catalog.xml";

    @Override
    public InputStream getXMLDocumentInputStream() {
        return getClass().getClassLoader().getResourceAsStream(FILE_PATH);
    }

}
