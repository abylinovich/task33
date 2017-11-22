package by.epam.task33.service;

import by.epam.task33.service.impl.SAXParseService;

import java.util.HashMap;
import java.util.Map;

public class ParseServiceFactory {

    private static final ParseServiceFactory factory = new ParseServiceFactory();
    private static final Map<XMLParserType, ParseService> parsers = new HashMap<>();

    static {
        parsers.put(XMLParserType.SAX, new SAXParseService());
//        parsers.put(XMLParserType.STAX, new StAXParseService());
//        parsers.put(XMLParserType.DOM, new DOMParseService());
    }

    private ParseServiceFactory() {

    }

    public static ParseServiceFactory getInstance() {
        return factory;
    }

    public static ParseService getParseService(String parserName) {
        return parsers.get(XMLParserType.valueOf(parserName.toUpperCase()));
    }

}