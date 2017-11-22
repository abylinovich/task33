package by.epam.task33.dao;

import by.epam.task33.dao.impl.XMLDocumentDAOImpl;

public class XMLDocumentDAOFactory {

    private static final XMLDocumentDAOFactory factory = new XMLDocumentDAOFactory();
    private static final XMLDocumentDAO documentDAO = new XMLDocumentDAOImpl();

    private XMLDocumentDAOFactory() {

    }

    public static XMLDocumentDAOFactory getInstance() {
        return factory;
    }

    public static XMLDocumentDAO getDocumentDAO() {
        return documentDAO;
    }

}