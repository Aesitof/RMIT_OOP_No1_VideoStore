package com.no1.geniestore;

import org.w3c.dom.Document;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.OutputStream;

public class Parser {
    /**
     * Write new Doc to XML file
     * @param doc
     * @param output
     * @throws TransformerException
     */
    public static void writeXml(Document doc,
                                OutputStream output)
            throws TransformerException {
        // XSLT for XML file format
        String XSLT_FILENAME = "xslt/format.xslt";

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer(new StreamSource(new File(XSLT_FILENAME)));

        // pretty print XML
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);

    }
}
