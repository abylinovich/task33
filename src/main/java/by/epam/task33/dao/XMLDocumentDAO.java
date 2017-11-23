package by.epam.task33.dao;

import java.io.FileNotFoundException;
import java.io.InputStream;

public interface XMLDocumentDAO {

    InputStream getXMLDocumentInputStream() throws FileNotFoundException;

}
