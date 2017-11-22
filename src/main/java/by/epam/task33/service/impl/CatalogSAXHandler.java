package by.epam.task33.service.impl;

import by.epam.task33.entity.Book;
import by.epam.task33.service.CatalogTagType;
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
        setProperty(qName);

    }

    private void setProperty(String qName) {
        CatalogTagType property = CatalogTagType.valueOf(qName.toUpperCase());
        switch (property) {
            case GENRE:
                book.setGenre(characters.toString());
                break;
            case TITLE:
                book.setTitle(characters.toString());
                break;
            case PRICE:
                book.setPrice(Double.parseDouble(characters.toString()));
                break;
            case AUTHOR:
                book.setAuthor(characters.toString());
                break;
            case DESCRIPTION:
                book.setDescription(characters.toString());
                break;
            case PUBLISH_DATE:
                book.setPublishDate(characters.toString());
                break;
            case BOOK:
                catalog.add(book);
                break;
            case CATALOG:
                break;
        }
    }

}
