package com.no1.geniestore.storage;

import org.w3c.dom.Document;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.no1.geniestore.GenieStoreApplication;
import com.no1.geniestore.commons.PathUtil;

public class Parser {
    public static final String XSLT_FILEPATH = PathUtil.PATH_RESOURCES + "/xslt/format.xslt";
    
    /**
     * Write new Doc to XML file
     * @param doc
     * @param output
     * @throws TransformerException
     */
    public static void writeXml(Document doc,
                                OutputStream output)
            throws TransformerException, FileNotFoundException {
        
       // Load XSLT file as a resource stream
        try (InputStream xsltStream = Parser.class.getResourceAsStream("/com/no1/geniestore/xslt/format.xslt")) {
            if (xsltStream == null) {
                throw new FileNotFoundException("Could not find XSLT file at /com/no1/geniestore/xslt/format.xslt");
            }

            // Initialize TransformerFactory and Transformer using the StreamSource from the InputStream
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer(new StreamSource(xsltStream));
    
            // Pretty-print XML output
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    
            // Transform the XML Document to the specified OutputStream
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(output);
    
            transformer.transform(source, result);

        } catch (IOException e) {
            throw new TransformerException("Error reading XSLT file", e);
        }

    }
}
