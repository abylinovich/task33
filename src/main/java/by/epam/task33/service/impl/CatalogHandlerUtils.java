package by.epam.task33.service.impl;

import by.epam.task33.entity.Book;
import by.epam.task33.service.CatalogTagType;

import java.util.List;

public class CatalogHandlerUtils {

    private CatalogHandlerUtils() {
    }

    public static void setProperty(List<Book> catalog, Book book, CatalogTagType property, String value) {
        switch (property) {
            case GENRE:
                book.setGenre(value);
                break;
            case TITLE:
                book.setTitle(value);
                break;
            case PRICE:
                book.setPrice(Double.parseDouble(value));
                break;
            case AUTHOR:
                book.setAuthor(value);
                break;
            case DESCRIPTION:
                book.setDescription(value);
                break;
            case PUBLISH_DATE:
                book.setPublishDate(value);
                break;
            case BOOK:
                catalog.add(book);
                break;
            case CATALOG:
                break;
        }
    }

}
