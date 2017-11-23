package by.epam.task33.service.impl;


import by.epam.task33.entity.Book;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CatalogSTAXHandler {

    private static final String BOOK = "book";
    private static final String ID_ATTRIBUTE = "id";

    private XMLEventReader xmlEventReader;
    private List<Book> catalog = new ArrayList<>();
    private Book book;
    private StringBuilder characters;

    public List<Book> getCatalog() {
        return catalog;
    }

    public CatalogSTAXHandler(XMLEventReader xmlEventReader) {
        this.xmlEventReader = xmlEventReader;
    }

    public List<Book> parse() throws XMLStreamException {
//        boolean bFirstName = false;
//        boolean bLastName = false;
//        boolean bNickName = false;
//        boolean bMarks = false;
//
//        while(xmlEventReader.hasNext()) {
//            XMLEvent event = xmlEventReader.nextEvent();
//
//            switch(event.getEventType()) {
//
//                case XMLStreamConstants.START_ELEMENT:
//                    StartElement startElement = event.asStartElement();
//                    String qName = startElement.getName().getLocalPart();
//
//                    if (qName.equalsIgnoreCase("student")) {
//                        System.out.println("Start Element : student");
//                        Iterator<Attribute> attributes = startElement.getAttributes();
//                        String rollNo = attributes.next().getValue();
//                        System.out.println("Roll No : " + rollNo);
//                    } else if (qName.equalsIgnoreCase("firstname")) {
//                        bFirstName = true;
//                    } else if (qName.equalsIgnoreCase("lastname")) {
//                        bLastName = true;
//                    } else if (qName.equalsIgnoreCase("nickname")) {
//                        bNickName = true;
//                    }
//                    else if (qName.equalsIgnoreCase("marks")) {
//                        bMarks = true;
//                    }
//                    break;
//
//                case XMLStreamConstants.CHARACTERS:
//                    Characters characters = event.asCharacters();
//                    if(bFirstName) {
//                        System.out.println("First Name: " + characters.getData());
//                        bFirstName = false;
//                    }
//                    if(bLastName) {
//                        System.out.println("Last Name: " + characters.getData());
//                        bLastName = false;
//                    }
//                    if(bNickName) {
//                        System.out.println("Nick Name: " + characters.getData());
//                        bNickName = false;
//                    }
//                    if(bMarks) {
//                        System.out.println("Marks: " + characters.getData());
//                        bMarks = false;
//                    }
//                    break;
//
//                case XMLStreamConstants.END_ELEMENT:
//                    EndElement endElement = event.asEndElement();
//
//                    if(endElement.getName().getLocalPart().equalsIgnoreCase("student")) {
//                        System.out.println("End Element : student");
//                        System.out.println();
//                    }
//                    break;
//            }
//        }
        return null;
    }


}
