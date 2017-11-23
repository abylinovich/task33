package by.epam.task33.service;

import by.epam.task33.entity.Book;
import by.epam.task33.exception.ParseException;

import java.util.List;

public interface ParseService {

    List<Book> parse() throws ParseException;

}
