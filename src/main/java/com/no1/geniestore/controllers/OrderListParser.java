package com.no1.geniestore.controllers;

import com.no1.geniestore.accounts.Account;
import com.no1.geniestore.products.Item;
import com.no1.geniestore.products.Order;
import com.no1.geniestore.products.OrderDetails;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class OrderListParser {
    private DocumentBuilder builder;

    /**
     * Construct a parser that can parse order lists
     */
    public OrderListParser() throws ParserConfigurationException {
        builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }

    /**
     * Parses an XML file containing an order list. returns an array list
     * containing all orders in the XML file
     */
    public List<Order> parse(String fileName) throws SAXException, IOException, ParserConfigurationException, ParseException {
        // get the <items> root element
        Element root = builder.parse(new File(fileName)).getDocumentElement();
        return getOrders(root);
    }

    /**
     * Obtains an array of orders from a DOM element e an <orders> element returns
     * the children of e, an array of all orders
     */
    private List<Order> getOrders(Element e) throws ParserConfigurationException, ParseException {
        List<Order> orders = new ArrayList<Order>();
        NodeList children = e.getChildNodes();

        for (int i = 0; i < children.getLength(); i++)
        {
            Node childNode = children.item(i);
            if (childNode instanceof Element)
            {
                Element childElement = (Element) childNode;
                if (childElement.getTagName().equals("order"))
                    orders.add(getOrder(childElement));
            }
        }
        return orders;
    }

    /**
     * Obtains an order from a DOM element. Argument passed e an <order> element
     * returns the order described by the given element
     */
    private Order getOrder(Element e) throws ParserConfigurationException, ParseException {
        NodeList children = e.getChildNodes();
        String orderID = null;
        Account owner = null;
        HashMap<Item, OrderDetails> itemList = null;
        double total = 0.0;
        double totalDiscount = 0.0;
        for (int j = 0; j < children.getLength(); j++)
        {
            Node childNode = children.item(j);
            if (childNode instanceof Element)
            {
                Element childElement = (Element) childNode;
                String tagName = childElement.getTagName();

                if (tagName.equals("orderID")) {
                    String data = ((Text) childElement.getFirstChild()).getData();
                    orderID = data;
                }
                else if (tagName.equals("owner")) {
                    owner = new AccountListParser().getAccount(childElement);
                }
                else if (tagName.equals("itemList")) {
                    itemList = getItemList(childElement);
                }
                else if (tagName.equals("total")) {
                    String data = ((Text) childElement.getFirstChild()).getData();
                    total = Double.parseDouble(data);
                }
                else if (tagName.equals("totalDiscount")) {
                    String data = ((Text) childElement.getFirstChild()).getData();
                    totalDiscount = Double.parseDouble(data);
                }
            }
        }
        return new Order(orderID, owner, itemList, total, totalDiscount);
    }

    /**
     * Obtains HashMap<Item, OrderDetails> for the getOrder method
     * @param e
     * @return
     */
    public HashMap<Item, OrderDetails> getItemList(Element e) throws ParserConfigurationException, ParseException {
        HashMap<Item, OrderDetails> itemList = new HashMap<>();
        NodeList children = e.getChildNodes(); // list of singleItem

        for (int i = 0; i < children.getLength(); i++)
        {
            Node childNode = children.item(i); //
            if (childNode instanceof Element)
            {
                Element childElement = (Element) childNode;
                if (childElement.getTagName().equals("singleItem")) {
                    Item item = getOrderItem(childElement);
                    OrderDetails orderDetails = getOrderDetails(childElement);
                    itemList.put(item, orderDetails);
                }

            }
        }

        return itemList;
    }

    public Item getOrderItem(Element e) throws ParserConfigurationException {
        Item item = null;
        NodeList children = e.getChildNodes();

        for (int j = 0; j < children.getLength(); j++)
        {
            Node childNode = children.item(j);
            if (childNode instanceof Element)
            {
                Element childElement = (Element) childNode;
                String tagName = childElement.getTagName();
                if (tagName.equals("item"))
                    item = new ItemListParser().getItem(childElement);
            }
        }

        return item;
    }

    //Create the Date format using SimpleDateFormat
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public OrderDetails getOrderDetails(Element e) throws ParseException {
        NodeList children = e.getChildNodes();
        Date returnDate = null;
        Date loanDate = null;
        int amount = 0;
        boolean isReturned = false;
        double discount = 0.0;

        for (int j = 0; j < children.getLength(); j++)
        {
            Node childNode = children.item(j);
            if (childNode instanceof Element)
            {
                Element childElement = (Element) childNode;
                String tagName = childElement.getTagName();
                String data = ((Text) childElement.getFirstChild()).getData();
                if (tagName.equals("returnDate")) {
                    returnDate = dateFormat.parse(data);
                }
                else if (tagName.equals("loanDate")) {
                    loanDate = dateFormat.parse(data);
                }
                else if (tagName.equals("amount")) {
                    amount = Integer.parseInt(data);
                }
                else if (tagName.equals("isReturned")) {
                    isReturned = (data.equals("true")) ? true : false;
                }
                else if (tagName.equals("discount")) {
                    discount = Double.parseDouble(data);
                }
            }
        }

        return new OrderDetails(returnDate, loanDate, amount, isReturned, discount);
    }


}
