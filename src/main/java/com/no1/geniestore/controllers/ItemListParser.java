/* Acknowledgment:
- OOP - INTE2512 - week 10 Files and Streams
- write to XML: https://mkyong.com/java/how-to-create-xml-file-in-java-dom/
* */

package com.no1.geniestore.controllers;

import static com.no1.geniestore.storage.Storage.ITEMS_FILE_PATH;
import static java.util.Objects.requireNonNull;

import com.no1.geniestore.Parser;
import com.no1.geniestore.constants.Genre;
import com.no1.geniestore.constants.ItemType;
import com.no1.geniestore.constants.LoanType;
import com.no1.geniestore.products.Item;
import com.no1.geniestore.products.ManagementSystem;
import com.no1.geniestore.products.Stock;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.util.HashMap;
import java.util.Optional;

public class ItemListParser {
    private DocumentBuilder builder;

    /**
     * Construct a parser that can parse item lists
     */
    public ItemListParser() throws ParserConfigurationException {
        builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }
    
    /**
     * Reads an items file from the given file path and returns an {@code Optional} containing the parsed items list {@code HashMap} with number of copies.
     * If the file is not found or an error occurs during parsing, {@code Optional.empty()} is returned.
     *
     * @param filePath the path to the accounts file; cannot be null.
     */
    public Optional<HashMap<Item, Integer>> readItemsCopiesFile(String filePath) {
        requireNonNull(filePath);
        
        try {
             HashMap<Item, Integer> itemList = parseItemTotal(ITEMS_FILE_PATH);
             return Optional.of(itemList);
        } catch (SAXException | IOException e) {
            return Optional.empty();
        }
    }
    
    /**
     * Reads an items file from the given file path and returns an {@code Optional} containing the parsed stock list {@code HashMap} with number of remaining copies.
     * If the file is not found or an error occurs during parsing, {@code Optional.empty()} is returned.
     *
     * @param filePath the path to the accounts file; cannot be null.
     */
    public Optional<HashMap<Item, Integer>> readItemsStockFile(String filePath) {
        requireNonNull(filePath);
        
        try {
            HashMap<Item, Integer> stockList = parseStockList(ITEMS_FILE_PATH);
            return Optional.of(stockList);
        } catch (SAXException | IOException e) {
            return Optional.empty();
        }
    }
    
    /**
     * Reads an items file from the given file path and returns an {@code Optional} containing the parsed {@code Observable<ItemData>}.
     * If the file is not found or an error occurs during parsing, {@code Optional.empty()} is returned.
     *
     * @param filePath the path to the accounts file; cannot be null.
     */
    public Optional<ObservableList<ItemData>> readItemsDataFromFile(String filePath) {
        requireNonNull(filePath);
        
        try {
            return Optional.of(parse(filePath));
        } catch (SAXException | IOException e) {
            return Optional.empty();
        }
    }
    
    
    /**
     * Parses an XML file containing an item list. returns an array list
     * containing all items in the XML file
     */
    public ObservableList<ItemData> parse(String fileName) throws SAXException, IOException {
        // get the <items> root element
        Element root = builder.parse(new File(fileName)).getDocumentElement();
        return getItems(root);
    }



    /**
     * Obtains an array of items from a DOM element e an <items> element returns
     * the children of e, an array of all items
     */
    private ObservableList<ItemData> getItems(Element e) {
        ObservableList<ItemData> items = FXCollections.observableArrayList();
        NodeList children = e.getChildNodes();

        for (int i = 0; i < children.getLength(); i++)
        {
            Node childNode = children.item(i);
            if (childNode instanceof Element)
            {
                Element childElement = (Element) childNode;
                if (childElement.getTagName().equals("item"))
                    items.add(getItemData(childElement));
            }
        }
        return items;
    }

    public ItemData getItemData(Element e) {
        NodeList children = e.getChildNodes();
        String id = null;
        String title = null;
        int year = 0;
        ItemType itemType = null;
        Genre genre = null;
        LoanType loanType = null;
        double rentalFee = 0.0;
        String image = null;
        int totalCopies = 0;
        int remainingCopies = 0;

        for (int j = 0; j < children.getLength(); j++)
        {
            Node childNode = children.item(j);
            if (childNode instanceof Element)
            {
                Element childElement = (Element) childNode;
                String tagName = childElement.getTagName();
                String data = ((Text) childElement.getFirstChild()).getData();
                if (tagName.equals("id")) {
                    id = data;
                }
                else if (tagName.equals("title")) {
                    title = data;
                }
                else if (tagName.equals("year")) {
                    year = Integer.parseInt(data);
                }
                else if (tagName.equals("itemType")) {
                    itemType = ItemType.byName(data);
                }
                else if (tagName.equals("genre")) {
                    genre = Genre.byName(data);
                }
                else if (tagName.equals("loanType")) {
                    loanType = LoanType.byName(data);
                }
                else if (tagName.equals("rentalFee"))
                {
                    rentalFee = Double.parseDouble(data);
                }
                else if (tagName.equals("image")) {
                    image = data;
                }
                else if (tagName.equals("copies")) {
                    totalCopies = Integer.parseInt(data);
                }
                else if (tagName.equals("remaining")) {
                    remainingCopies = Integer.parseInt(data);
                }
            }
        }
        return new ItemData(id, title, year, itemType, genre, loanType, rentalFee, image, totalCopies, remainingCopies);
    }

    /**
     * Parse XML to the itemList HashMap in ManagementSystem
     * @param fileName
     * @return
     * @throws SAXException
     * @throws IOException
     */
    public HashMap<Item, Integer> parseItemTotal(String fileName) throws SAXException, IOException {
        // get the <items> root element
        Element root = builder.parse(new File(fileName)).getDocumentElement();
        return getItemListTotal(root);
    }

    /**
     * Obtains HashMap<Item, Integer> for the parseItemTotal method
     * @param e
     * @return
     */
    public HashMap<Item, Integer> getItemListTotal(Element e) {
        HashMap<Item, Integer> itemListTotal = new HashMap<>();
        NodeList children = e.getChildNodes(); // list of singleItem

        for (int i = 0; i < children.getLength(); i++)
        {
            Node childNode = children.item(i); //
            if (childNode instanceof Element)
            {
                Element childElement = (Element) childNode;
                if (childElement.getTagName().equals("item"))
                    itemListTotal.put(getItem(childElement), getTotalCopies(childElement)); // not yet parse integer
            }
        }

        return itemListTotal;
    }

    public HashMap<Item, Integer> parseStockList(String fileName) throws IOException, SAXException {
        // get the <items> root element
        Element root = builder.parse(new File(fileName)).getDocumentElement();
        return getStockList(root);
    }

    public HashMap<Item, Integer> getStockList(Element e) {
        HashMap<Item, Integer> stockList = new HashMap<>();
        NodeList children = e.getChildNodes();

        for (int i = 0; i < children.getLength(); i++)
        {
            Node childNode = children.item(i);
            if (childNode instanceof Element)
            {
                Element childElement = (Element) childNode;
                if (childElement.getTagName().equals("item"))
                    stockList.put(getItem(childElement), getRemainingCopies(childElement)); // not yet parse integer
            }
        }

        return stockList;
    }

    Item getItem(Element e) {
        NodeList children = e.getChildNodes();
        String id = null;
        String title = null;
        int year = 0;
        ItemType itemType = null;
        Genre genre = null;
        LoanType loanType = null;
        double rentalFee = 0.0;
        String image = null;

        for (int j = 0; j < children.getLength(); j++)
        {
            Node childNode = children.item(j);
            if (childNode instanceof Element)
            {
                Element childElement = (Element) childNode;
                String tagName = childElement.getTagName();
                String data = ((Text) childElement.getFirstChild()).getData();
                if (tagName.equals("id")) {
                    id = data;
                }
                else if (tagName.equals("title")) {
                    title = data;
                }
                else if (tagName.equals("year")) {
                    year = Integer.parseInt(data);
                }
                else if (tagName.equals("itemType")) {
                    itemType = ItemType.byName(data);
                }
                else if (tagName.equals("genre")) {
                    genre = Genre.byName(data);
                }
                else if (tagName.equals("loanType")) {
                    loanType = LoanType.byName(data);
                }
                else if (tagName.equals("rentalFee"))
                {
                    rentalFee = Double.parseDouble(data);
                }
                else if (tagName.equals("image")) {
                    image = data;
                }
            }
        }
        return new Item(id, title, year, itemType, genre, loanType, rentalFee, image);
    }

    public int getTotalCopies(Element e) {
        NodeList children = e.getChildNodes();
        int totalCopies = 0;

        for (int j = 0; j < children.getLength(); j++)
        {
            Node childNode = children.item(j);
            if (childNode instanceof Element)
            {
                Element childElement = (Element) childNode;
                String tagName = childElement.getTagName();
                String data = ((Text) childElement.getFirstChild()).getData();
                if (tagName.equals("copies")) {
                    totalCopies = Integer.parseInt(data);
                }
            }
        }

        return totalCopies;
    }

    public int getRemainingCopies(Element e) {
        NodeList children = e.getChildNodes();
        int remainingCopies = 0;

        for (int j = 0; j < children.getLength(); j++)
        {
            Node childNode = children.item(j);
            if (childNode instanceof Element)
            {
                Element childElement = (Element) childNode;
                String tagName = childElement.getTagName();
                String data = ((Text) childElement.getFirstChild()).getData();
                if (tagName.equals("remaining")) {
                    remainingCopies = Integer.parseInt(data);
                }
            }
        }

        return remainingCopies;
    }


    /**
     * Save the in-use list of Item to the XML file when needed
     * @param
     * @throws ParserConfigurationException
     */
    public static void saveItemFile() throws ParserConfigurationException, FileNotFoundException, TransformerException {
        // new DOM
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        // root Element
        Element root = document.createElement("items");
        document.appendChild(root);

        // Create child nodes
        for (Item item : ManagementSystem.itemList.keySet()) {
            root.appendChild(itemToXML(item, document, ManagementSystem.itemList.get(item)));
        }

        Parser.writeXml(document, new FileOutputStream(ITEMS_FILE_PATH));
    }

    public static Element itemToXML(Item newItem, Document document, int totalCopies) {
        Element item = document.createElement("item");

        Element id = document.createElement("id");
        id.setTextContent(newItem.getId());
        item.appendChild(id);

        Element title = document.createElement("title");
        title.setTextContent(newItem.getTitle());
        item.appendChild(title);

        Element year = document.createElement("year");
        year.setTextContent(String.valueOf(newItem.getYear()));
        item.appendChild(year);

        Element itemType = document.createElement("itemType");
        itemType.setTextContent(newItem.getItemType().toString());
        item.appendChild(itemType);

        try {
            Element genre = document.createElement("genre");
            genre.setTextContent(newItem.getGenre().toString());
            item.appendChild(genre);
        } catch (NullPointerException exception) {
//            exception.printStackTrace();
        }

        Element loanType = document.createElement("loanType");
        loanType.setTextContent(newItem.getLoanType().toString());
        item.appendChild(loanType);

        Element rentalFee = document.createElement("rentalFee");
        rentalFee.setTextContent(String.valueOf(newItem.getRentalFee()));
        item.appendChild(rentalFee);

        Element image = document.createElement("image");
        image.setTextContent(newItem.getImage());
        item.appendChild(image);

        Element copies = document.createElement("copies");
        copies.setTextContent(String.valueOf(totalCopies));
        item.appendChild(copies);

        Element remaining = document.createElement("remaining");
        for (Item stock : Stock.stockList.keySet()) {
            if (stock.getId().equals(newItem.getId())) {
                remaining.setTextContent(String.valueOf(Stock.stockList.get(stock)));
                break;
            }
        }
        item.appendChild(remaining);

        return item;
    }

    public static Element orderItemToXML(Item newItem, Document document) {
        Element item = document.createElement("item");

        Element id = document.createElement("id");
        id.setTextContent(newItem.getId());
        item.appendChild(id);

        Element title = document.createElement("title");
        title.setTextContent(newItem.getTitle());
        item.appendChild(title);

        Element year = document.createElement("year");
        year.setTextContent(String.valueOf(newItem.getYear()));
        item.appendChild(year);

        Element itemType = document.createElement("itemType");
        itemType.setTextContent(newItem.getItemType().toString());
        item.appendChild(itemType);

        try {
            Element genre = document.createElement("genre");
            genre.setTextContent(newItem.getGenre().toString());
            item.appendChild(genre);
        } catch (NullPointerException exception) {
//            exception.printStackTrace();
        }

        Element loanType = document.createElement("loanType");
        loanType.setTextContent(newItem.getLoanType().toString());
        item.appendChild(loanType);

        Element rentalFee = document.createElement("rentalFee");
        rentalFee.setTextContent(String.valueOf(newItem.getRentalFee()));
        item.appendChild(rentalFee);

        Element image = document.createElement("image");
        image.setTextContent(newItem.getImage());
        item.appendChild(image);

        return item;
    }
}
