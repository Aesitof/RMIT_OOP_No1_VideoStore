package com.no1.geniestore.storage;

import org.w3c.dom.Document;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.net.URL;

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
        
        URL xsltUrl = Parser.class.getResource("/com/no1/geniestore/xslt/format.xslt");
        if (xsltUrl == null) {
            throw new FileNotFoundException("Could not find xslt file");
        }
        File xsltFile = new File(xsltUrl.getFile());
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer(new StreamSource(xsltFile));

        // pretty print XML
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);

    }
}
