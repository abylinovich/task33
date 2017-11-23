package by.epam.task33.service.impl.sax;

import by.epam.task33.entity.Book;
import by.epam.task33.service.CatalogTagType;
import by.epam.task33.service.impl.CatalogHandlerUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class CatalogSAXHandler extends DefaultHandler {

    private static final String BOOK = "book";
    private static final String ID_ATTRIBUTE = "id";

    private List<Book> catalog = new ArrayList<>();
    private Book book;
    private StringBuilder characters;

    public List<Book> getCatalog() {
        return catalog;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        characters = new StringBuilder();
        if(qName.equals(BOOK)) {
            book = new Book();
            book.setId(Integer.parseInt(attributes.getValue(ID_ATTRIBUTE)));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        characters.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        CatalogTagType property = CatalogTagType.valueOf(qName.toUpperCase());
        String value = characters.toString();
        CatalogHandlerUtils.setProperty(catalog, book, property, value);
    }

}
