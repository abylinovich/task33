package by.epam.task33.service.impl.dom;

import by.epam.task33.entity.Book;
import by.epam.task33.service.CatalogTagType;
import by.epam.task33.service.impl.CatalogHandlerUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class CatalogDOMHandler {

    private static final String BOOK_TAG = "book";
    private static final String ID_ATTRIBUTE = "id";

    private List<Book> catalog = new ArrayList<>();

    public List<Book> getCatalog() {
        return catalog;
    }

    public void parse(Document document) {
        document.normalizeDocument();
        NodeList booksList = document.getElementsByTagName(BOOK_TAG);
        for(int i = 0; i < booksList.getLength(); i++) {
            Node bookNode = booksList.item(i);
            createBookBean(bookNode);
        }
    }

    private void createBookBean(Node bookNode) {
        Book book = new Book();
        String id = bookNode.getAttributes().getNamedItem(ID_ATTRIBUTE).getNodeValue();
        book.setId(Integer.parseInt(id));

        NodeList bookParametersList = bookNode.getChildNodes();
        for(int j = 0; j < bookParametersList.getLength(); j++) {
            Node parameter = bookParametersList.item(j);
            if(parameter.getNodeType() == Node.ELEMENT_NODE) {
                CatalogTagType property = CatalogTagType.valueOf(parameter.getNodeName().toUpperCase());
                String value = parameter.getTextContent();
                CatalogHandlerUtils.setProperty(catalog, book, property, value);
            }
        }
        catalog.add(book);
    }

}
