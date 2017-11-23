package by.epam.task33.service.impl.stax;


import by.epam.task33.entity.Book;
import by.epam.task33.service.CatalogTagType;
import by.epam.task33.service.impl.CatalogHandlerUtils;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.util.ArrayList;
import java.util.List;

public class CatalogSTAXHandler {

    private static final String BOOK = "book";
    private static final String ID_ATTRIBUTE = "id";

    private List<Book> catalog = new ArrayList<>();
    private Book book;
    private StringBuilder characters;

    public List<Book> getCatalog() {
        return catalog;
    }

    public void parse(XMLEventReader eventReader) throws XMLStreamException {

        while(eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();
            switch(event.getEventType()) {
                case XMLStreamConstants.START_ELEMENT:
                    StartElement startElement = event.asStartElement();
                    String qName = startElement.getName().getLocalPart();
                    characters = new StringBuilder();
                    if(qName.equals(BOOK)) {
                        book = new Book();
                        String id = startElement.getAttributeByName(new QName(ID_ATTRIBUTE)).getValue();
                        book.setId(Integer.parseInt(id));
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    characters.append(event.asCharacters().getData());
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    EndElement endElement = event.asEndElement();
                    CatalogTagType property = CatalogTagType.valueOf(endElement.getName().getLocalPart().toUpperCase());
                    String value = characters.toString();
                    CatalogHandlerUtils.setProperty(catalog, book, property, value);
                    break;
            }
        }
    }

}
